package com.example.countries.listCountries.di

import androidx.lifecycle.ViewModel
import com.example.countries.di.ViewModelKey
import com.example.countries.listCountries.ListCountriesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ListCountriesModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListCountriesViewModel::class)
    abstract fun bindViewModel(viewmodel: ListCountriesViewModel): ViewModel
}