package com.example.countries.testUtils

import com.example.countries.CountriesApplication
import com.example.countries.di.AppComponent
import com.example.countries.di.DaggerTestAppComponent


class CountriesTestApplication : CountriesApplication() {

  override fun initializeComponent(): AppComponent {
       return DaggerTestAppComponent.create()
  }
}