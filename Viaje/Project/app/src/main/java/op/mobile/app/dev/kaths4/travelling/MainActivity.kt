package op.mobile.app.dev.kaths4.travelling


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.view.ContextThemeWrapper
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private var backPressedTime: Long = 0
    lateinit var message: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_countries,
                R.id.navigation_phrases,
                R.id.navigation_quiz,
                R.id.navigation_settings,
                R.id.navigation_translation,
                R.id.navigation_github_countries
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val isDarkMode: Boolean = sharedPref.getBoolean("dark_mode", false)
        if (isDarkMode) {
            (this as AppCompatActivity?)!!.delegate.localNightMode =
                AppCompatDelegate.MODE_NIGHT_YES
        } else {
            (this as AppCompatActivity?)!!.delegate.localNightMode =
                AppCompatDelegate.MODE_NIGHT_NO
        }
    }

    @SuppressLint("ShowToast")
    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            message.cancel()
            val exitAlertDialog =
                AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogTheme))
            exitAlertDialog.setTitle(R.string.app_name)
            exitAlertDialog.setIcon(R.drawable.exit_img)
            exitAlertDialog.setCancelable(false)
            exitAlertDialog.setNeutralButton(R.string.exit_dialog_cancel) { dialog, _ -> dialog.cancel() }
            exitAlertDialog.setMessage(getString(R.string.Exit_dialog_message))
            exitAlertDialog.setNegativeButton(R.string.exit_dialog_no) { dialog, _ -> dialog.cancel() }
            exitAlertDialog.setPositiveButton(R.string.exit_dialog_yes) { _, _ ->
                finish()
            }
            val dialog: AlertDialog = exitAlertDialog.create()
            dialog.show()
        } else {
            message =
                Toast.makeText(this, getString(R.string.exit_alert_message), Toast.LENGTH_LONG)
            message.show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}


