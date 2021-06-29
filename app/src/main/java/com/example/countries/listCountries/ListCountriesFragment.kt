package com.example.countries.listCountries

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countries.CountriesApplication
import com.example.countries.databinding.FragmentListCountriesBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class ListCountriesFragment : Fragment(), AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private val viewModel by viewModels<ListCountriesViewModel> {viewModelFactory}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val appComponent = (requireActivity().application as CountriesApplication).appComponent
        val listCountriesComponent = appComponent.listCountriesComponent().create()

        listCountriesComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListCountriesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initContinentsFilter(binding)
        initCountriesList(binding)


        return binding.root
    }

    private fun initContinentsFilter(binding: FragmentListCountriesBinding) {
        val spinner = binding.continentSpinner


        CustomSpinnerAdapter(requireContext(), android.R.layout.simple_spinner_item, filterContinents)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
                spinner.onItemSelectedListener = this

            }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if(position != 0){
            val continent = parent?.getItemAtPosition(position) as Continent
            val v : View? = activity?.findViewById(android.R.id.content)
            Snackbar.make(v!!, getSnackbarText(continent), Snackbar.LENGTH_LONG).show()
            viewModel.getCountries(continent.code)
        }
    }

    private fun getSnackbarText(continent: Continent) : String{
        return if(continent.code == ""){
            continent.name
        }else{
            "${continent.name} - ${continent.code}"
        }
        
    }

    private fun initCountriesList(binding: FragmentListCountriesBinding){
        binding.countriesList.layoutManager = LinearLayoutManager(requireContext())
        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.countriesList.addItemDecoration(decoration)
        binding.countriesList.adapter = CountriesListAdapter(
            CountryClickListener { code ->
                this.findNavController().navigate(
                    ListCountriesFragmentDirections.actionListCountriesFragmentToDetailsCountryFragment(code)
                )
            }
        )
    }

}