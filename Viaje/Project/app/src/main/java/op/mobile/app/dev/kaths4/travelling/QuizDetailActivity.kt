package op.mobile.app.dev.kaths4.travelling

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_quiz_detail.*
import op.mobile.app.dev.kaths4.travelling.model.Quiz
import op.mobile.app.dev.kaths4.travelling.ui.quiz.QuizDetailModel

class QuizDetailActivity : AppCompatActivity() {

    private lateinit var name: String

    private lateinit var viewModel: QuizDetailModel

    private lateinit var quizList: List<Quiz>

    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0

    private var mCurrentPosition: Int = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_detail)
        showQuizWelcomeDialog()

        viewModel = ViewModelProvider(this).get(QuizDetailModel::class.java)

        viewModel.response.observe(this, {
            quizList = it
            showQuestion(quizList)
        })
        option_one.setOnClickListener{
            selectedOption(option_one, 1)
        }
        option_two.setOnClickListener{
            selectedOption(option_two, 2)
        }
        option_three.setOnClickListener{
            selectedOption(option_three, 3)
        }
        option_four.setOnClickListener{
            selectedOption(option_four, 4)
        }
        submit_btn.setOnClickListener {
            if (mSelectedOptionPosition == 0){
mCurrentPosition ++
                when{
                    mCurrentPosition <= quizList.size ->{
                        showQuestion(quizList)
                    }
                    else ->{
                        val intent =
                            Intent(this@QuizDetailActivity, ResultActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            } else {
                val question = quizList?.get(mCurrentPosition - 1)

                if (question!!.question1.answer != mSelectedOptionPosition) {
                    answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_shape)
                }
                else {
                    mCorrectAnswers ++
                }

                answerView(question.question1.answer, R.drawable.correct_option_border_shape)

                if (mCurrentPosition == quizList!!.size) {
                    submit_btn.text = "FINISH"
                } else {
                    submit_btn.text = "GO TO NEXT QUESTION"
                }

                mSelectedOptionPosition = 0
            }
        }
    }

    private fun showQuizWelcomeDialog() {

        val dialogView = layoutInflater.inflate(R.layout.dialog_welcome_quiz, null)

        val customDialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .show()

        val btDismiss = dialogView.findViewById<Button>(R.id.btnStart)
        val editName = dialogView.findViewById<AppCompatEditText>(R.id.editName)

        btDismiss.setOnClickListener {

            if (editName.text.toString().isEmpty()) {
                Toast.makeText(this, getString(R.string.MSGEnterName), Toast.LENGTH_SHORT).show()

            } else {
                name = editName.text.toString()
                customDialog.dismiss()
                llQuestionView.visibility = View.VISIBLE
            }
        }
        customDialog.setCanceledOnTouchOutside(false)
        customDialog.setCancelable(false)
    }

    private fun showQuestion(quizList: List<Quiz>) {

        defaultOption()

        if (mCurrentPosition == quizList!!.size) { submit_btn.text = "FINISH"
        } else {
            submit_btn.text = "SUBMIT"
        }

        txtview_question.text = this.quizList[0].question1!!.question
        option_one.text = this.quizList[0].question1!!.option!!.option1
        option_two.text = this.quizList[0].question1!!.option!!.option2
        option_three.text = this.quizList[0].question1!!.option!!.option3
        option_four.text = this.quizList[0].question1!!.option!!.option4
    }

    private fun defaultOption(){
        val options = ArrayList<TextView>()
        options.add(0,option_one)
        options.add(1,option_two)
        options.add(2,option_three)
        options.add(3,option_four)

        for (option in options){
            option.setTextColor(Color.parseColor("#FF000000"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this, R.drawable.border_shape
            )

        }
    }

    private fun selectedOption(tv: TextView, selectedOptionNum: Int){
defaultOption()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#512DA8"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this, R.drawable.selected_option_border_shape
        )
    }

    private fun answerView(answer :Int, drawableView: Int){
        when(answer){
            1 -> {
                option_one.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                option_two.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                option_three.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 -> {
                option_four.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }
}