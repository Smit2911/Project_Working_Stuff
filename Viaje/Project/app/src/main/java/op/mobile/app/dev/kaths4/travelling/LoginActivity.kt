package op.mobile.app.dev.kaths4.travelling

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var createaccountclick: TextView
    lateinit var signinclick: Button
    lateinit var email: TextView
    lateinit var password: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        createaccountclick = findViewById(R.id.create_account)
        signinclick = findViewById(R.id.sign_in)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)

        createaccountclick.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }

        signinclick.setOnClickListener {
            login()
        }

    }

    private fun login() {
        if (email.text.toString().isEmpty()) {
            email.error = getString(R.string.ErrorMSGEmail)
            email.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            email.error = getString(R.string.ErrorMSGInvEmail)
            email.requestFocus()
            return
        }

        if (password.text.toString().isEmpty()) {
            password.error = getString(R.string.ErrorMSGPass)
            password.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    updateUI(null)
                }
            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {

        if (currentUser != null) {
            if (currentUser.isEmailVerified) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                Toast.makeText(
                    baseContext, getString(R.string.MSGVerifyEmail),
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else{
            Toast.makeText(
                baseContext, getString(R.string.MSGAuthFail),
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

}