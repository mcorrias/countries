package com.example.countries.di

import com.example.countries.detailsCountry.di.DetailsComponent
import com.example.countries.listCountries.di.ListCountriesComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApiModule::class,
    DispatcherProviderModule::class,
    ViewModelBuilderModule::class,
    AppSubcomponents::class])
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create() :AppComponent
    }

    fun listCountriesComponent() : ListCountriesComponent.Factory
    fun detailsComponent() : DetailsComponent.Factory


}