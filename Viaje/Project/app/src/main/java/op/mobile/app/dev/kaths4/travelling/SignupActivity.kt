package op.mobile.app.dev.kaths4.travelling

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var signupclick: Button
    lateinit var signupemail: TextView
    lateinit var signuppassword:TextView
    lateinit var createdaccountclick: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()
        signupclick = findViewById(R.id.sign_up)
        signupemail = findViewById(R.id.s_email)
        signuppassword = findViewById(R.id.s_password)
        createdaccountclick = findViewById(R.id.created_account)

        createdaccountclick.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        signupclick.setOnClickListener {
            signUpUser()

        }
    }

    private fun signUpUser (){
        if (signupemail.text.toString().isEmpty()){
            signupemail.error = getString(R.string.ErrorMSGEmail)
            signupemail.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(signupemail.text.toString()).matches()){
            signupemail.error = getString(R.string.ErrorMSGInvEmail)
            signupemail.requestFocus()
            return
        }

        if (signuppassword.text.toString().isEmpty()){
            signuppassword.error = getString(R.string.ErrorMSGPass)
            signuppassword.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(signupemail.text.toString(), signuppassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user!!.sendEmailVerification()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this,LoginActivity::class.java))
                                finish()
                            }
                        }

                } else {
                    Toast.makeText(baseContext, getString(R.string.MSGAuthFail),
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload(currentUser);
        }
    }

    private fun reload(currentUser: FirebaseUser?){

    }

    override fun onBackPressed(){
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }
}