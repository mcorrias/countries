package com.example.countries.di

import com.example.countries.detailsCountry.di.DetailsComponent
import com.example.countries.listCountries.di.ListCountriesComponent
import dagger.Module

@Module(subcomponents = [ListCountriesComponent::class, DetailsComponent::class])
class AppSubcomponents