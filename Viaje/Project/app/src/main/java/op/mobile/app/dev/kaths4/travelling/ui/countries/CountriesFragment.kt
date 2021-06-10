package op.mobile.app.dev.kaths4.travelling.ui.countries

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.recycler_view_item.*
import op.mobile.app.dev.kaths4.travelling.ui.map.MapsActivity
import op.mobile.app.dev.kaths4.travelling.service.ServiceAdapter
import op.mobile.app.dev.kaths4.travelling.R
import op.mobile.app.dev.kaths4.travelling.databinding.CountriesFragmentBinding

class CountriesFragment : Fragment(), ServiceAdapter.OnCountryClickListener {

    companion object {
        fun newInstance() = CountriesFragment()
    }

    private lateinit var viewModel: CountriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: CountriesFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.countries_fragment, container, false
        )
        viewModel = ViewModelProvider(this).get(CountriesViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.countriesViewModel = viewModel
        binding.rvCountries.adapter = ServiceAdapter(this)
        return binding.root
    }

    override fun onCountryClick(position: Int) {

        val intent = Intent(context, MapsActivity::class.java)
        startActivity(intent)

    }
}

