package com.example.countries.detailsCountry.di

import com.example.countries.detailsCountry.DetailsCountryFragment
import dagger.Subcomponent

@Subcomponent(modules = [DetailsModule::class])
interface DetailsComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): DetailsComponent
    }

    fun inject(fragment: DetailsCountryFragment)

}