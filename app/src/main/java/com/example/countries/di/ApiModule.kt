package com.example.countries.di

import com.example.countries.network.ApolloApiService
import com.example.countries.network.MainApolloApiService
import dagger.Binds
import dagger.Module

@Module
abstract class ApiModule {
    @Binds
    abstract fun provideApi(apiService: MainApolloApiService) : ApolloApiService
}