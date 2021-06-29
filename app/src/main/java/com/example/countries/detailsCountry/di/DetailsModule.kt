package com.example.countries.detailsCountry.di

import androidx.lifecycle.ViewModel
import com.example.countries.detailsCountry.DetailsCountryViewModel
import com.example.countries.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailsModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailsCountryViewModel::class)
    abstract fun bindViewModel(viewmodel: DetailsCountryViewModel): ViewModel
}