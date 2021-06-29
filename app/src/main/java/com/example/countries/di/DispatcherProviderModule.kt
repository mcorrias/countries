package com.example.countries.di

import com.example.countries.DefaultDispatcherProvider
import com.example.countries.DispatcherProvider
import com.example.countries.network.ApolloApiService
import com.example.countries.network.MainApolloApiService
import dagger.Binds
import dagger.Module

@Module
abstract class DispatcherProviderModule {

    @Binds
    abstract fun provideDispatcherProvider(dispatcherProvider: DefaultDispatcherProvider): DispatcherProvider
}