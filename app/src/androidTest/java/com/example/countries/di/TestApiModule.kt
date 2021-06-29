package com.example.countries.di

import com.example.countries.network.FakeApolloService
import com.example.countries.network.ApolloApiService
import dagger.Binds
import dagger.Module

@Module
abstract class TestApiModule {

    @Binds
    abstract fun provide(fakeApi : FakeApolloService) : ApolloApiService
}