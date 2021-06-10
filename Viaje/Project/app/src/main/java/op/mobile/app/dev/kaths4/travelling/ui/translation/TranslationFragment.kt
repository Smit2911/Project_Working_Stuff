package op.mobile.app.dev.kaths4.travelling.ui.translation

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.translation_fragment.*
import op.mobile.app.dev.kaths4.travelling.QuizDetailActivity
import op.mobile.app.dev.kaths4.travelling.R
import op.mobile.app.dev.kaths4.travelling.TranslationActivity

class TranslationFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.translation_fragment, container, false)

        val btnstarted = view.findViewById<Button>(R.id.btn_getstarted)

        btnstarted.setOnClickListener {
            val intent = Intent(context, TranslationActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}