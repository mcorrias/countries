package com.example.countries

import android.app.Application
import com.example.countries.di.AppComponent
import com.example.countries.di.DaggerAppComponent

open class CountriesApplication :Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent() : AppComponent{
        return DaggerAppComponent.factory().create()
    }
}