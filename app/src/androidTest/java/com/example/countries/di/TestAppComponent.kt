package com.example.countries.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    TestApiModule::class,
    ViewModelBuilderModule::class,
    TestDispatcherProviderModule::class,
    AppSubcomponents::class])

interface TestAppComponent : AppComponent