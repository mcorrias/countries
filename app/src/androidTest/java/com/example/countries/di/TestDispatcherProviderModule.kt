package com.example.countries.di

import com.example.countries.DispatcherProvider
import com.example.countries.testUtils.TestDispatcherProvider
import dagger.Binds
import dagger.Module

@Module
abstract class TestDispatcherProviderModule {

    @Binds
    abstract fun provideTestDispatcherProvider(dispatcherProvider: TestDispatcherProvider): DispatcherProvider
}