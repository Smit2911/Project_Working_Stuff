package op.mobile.app.dev.kaths4.travelling

import android.content.Intent
import android.media.MediaParser
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreenActivity : AppCompatActivity() {

    private var TIME_OUT:Long = 4000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Code for sound in splash screen
        val music = MediaPlayer.create(this,R.raw.travel)
        music.start()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            music.release()  // Release sound at the end of activity
            finish()
        }, TIME_OUT)
    }
}