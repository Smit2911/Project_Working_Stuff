package op.mobile.app.dev.kaths4.travelling.ui.settings

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import com.google.firebase.auth.FirebaseAuth
import op.mobile.app.dev.kaths4.travelling.R


class SettingsFragment : Fragment() {

    private lateinit var pref: SharedPreferences
    private lateinit var switch: SwitchCompat

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.settings_fragment, container, false)

        val btnTermsCondition = view.findViewById<Button>(R.id.btn_terms)
        val btnPrivacyPolicy = view.findViewById<Button>(R.id.btn_privacy)
        val btnLogout = view.findViewById<Button>(R.id.btn_logout)

        pref = activity?.getPreferences(Context.MODE_PRIVATE)!!
        switch = view.findViewById(R.id.sw_theme)

        val isDarkMode: Boolean = pref.getBoolean("dark_mode", false)
        if (isDarkMode) {
            (activity as AppCompatActivity?)!!.delegate.localNightMode =
                AppCompatDelegate.MODE_NIGHT_YES
        } else {
            (activity as AppCompatActivity?)!!.delegate.localNightMode =
                AppCompatDelegate.MODE_NIGHT_NO
        }
        switch.isChecked = isDarkMode == true
        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                (activity as AppCompatActivity?)!!.delegate.localNightMode =
                    AppCompatDelegate.MODE_NIGHT_YES
                pref.edit().putBoolean("dark_mode", isChecked).apply()
            } else {
                (activity as AppCompatActivity?)!!.delegate.localNightMode =
                    AppCompatDelegate.MODE_NIGHT_NO
                pref.edit().putBoolean("dark_mode", isChecked).apply()
            }
        }

        btnPrivacyPolicy.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.android-examples.com/open-website-url-in-androids-web-browser-from-application/")
            )
            startActivity(intent)
        }

        btnTermsCondition.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.android-examples.com/open-website-url-in-androids-web-browser-from-application/")
            )
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
        }
        return view
    }

}