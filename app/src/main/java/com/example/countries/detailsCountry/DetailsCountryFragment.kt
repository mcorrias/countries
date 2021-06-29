package com.example.countries.detailsCountry

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.countries.CountriesApplication
import com.example.countries.databinding.FragmentDetailsCountryBinding
import javax.inject.Inject


class DetailsCountryFragment : Fragment(){
    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    private val viewModel by viewModels<DetailsCountryViewModel> {viewModelFactory}

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val appComponent = (requireActivity().application as CountriesApplication).appComponent
        val detailsComponent = appComponent.detailsComponent().create()
        detailsComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailsCountryBinding.inflate(inflater)
        val code = DetailsCountryFragmentArgs.fromBundle(requireArguments()).code
        binding.lifecycleOwner = this
        viewModel.setCode(code)
        viewModel.getDetailsCountry(code)
        binding.viewModel = viewModel


        return binding.root
    }



}