package com.example.countries.network

import com.apollographql.apollo.api.Response
import com.example.countries.CountriesListQuery
import com.example.countries.DetailsCountryQuery

interface ApolloApiService {
    suspend fun getCountries(filter : String = "") : List<CountriesListQuery.Country>?
    suspend fun getDetailsCountry(code : String) : DetailsCountryQuery.Country?
}