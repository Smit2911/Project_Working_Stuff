package op.mobile.app.dev.kaths4.travelling.ui.phrases

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import op.mobile.app.dev.kaths4.travelling.*
import op.mobile.app.dev.kaths4.travelling.databinding.PhrasesFragmentBinding
import op.mobile.app.dev.kaths4.travelling.service.ServiceAdapter



class PhrasesFragment : Fragment(), ServiceAdapter.OnCountryClickListener {
    private lateinit var viewModel: PhrasesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: PhrasesFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.phrases_fragment, container, false
        )
        viewModel = ViewModelProvider(this).get(PhrasesViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.phrasesViewModel = viewModel
        binding.rvCountries.adapter = ServiceAdapter(this)
        return binding.root
    }

    override fun onCountryClick(position: Int) {

        if (position == 0) {
            val intent = Intent(context, SouthAfricaPhrasesActivity::class.java)
            startActivity(intent)
        } else if (position == 1) {
            val intent = Intent(context, MauritiusPhrasesActivity::class.java)
            startActivity(intent)
        } else if (position == 2) {
            val intent = Intent(context, IndiaPhrasesActivity::class.java)
            startActivity(intent)
        } else if (position == 3) {
            val intent = Intent(context, SingaporePhrasesActivity::class.java)
            startActivity(intent)
        } else if (position == 4) {
            val intent = Intent(context, ItalyPhrasesActivity::class.java)
            startActivity(intent)
        } else if (position == 5) {
            val intent = Intent(context, UnitedKingdomPhrasesActivity::class.java)
            startActivity(intent)
        } else if (position == 6) {
            val intent = Intent(context, CanadaPhrasesActivity::class.java)
            startActivity(intent)
        } else if (position == 7) {
            val intent = Intent(context, UsPhrasesActivity::class.java)
            startActivity(intent)
        } else if (position == 8) {
            val intent = Intent(context, BrazilPhrasesActivity::class.java)
            startActivity(intent)
        } else if (position == 9) {
            val intent = Intent(context, ColombiaPhrasesActivity::class.java)
            startActivity(intent)
        } else if (position == 10) {
            val intent = Intent(context, NewZealandPhrasesActivity::class.java)
            startActivity(intent)
        } else if (position == 11) {
            val intent = Intent(context, AustraliaPhrasesActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(
                activity,
                "Something went wrong, please try again later.",
                Toast.LENGTH_LONG
            ).show()
        }

    }

}