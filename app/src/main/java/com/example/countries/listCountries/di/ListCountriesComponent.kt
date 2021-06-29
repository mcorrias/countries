package com.example.countries.listCountries.di

import com.example.countries.listCountries.ListCountriesFragment
import dagger.Subcomponent

@Subcomponent(modules = [ListCountriesModule::class])
interface ListCountriesComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): ListCountriesComponent
    }

    fun inject(fragment: ListCountriesFragment)

}