package com.example.countries.network

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.example.countries.CountriesListQuery
import com.example.countries.DetailsCountryQuery
import com.example.countries.type.CountryFilterInput
import com.example.countries.type.StringQueryOperatorInput
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainApolloApiService @Inject constructor() : ApolloApiService {

    private var apolloInstance :ApolloClient? = null

    private fun apolloClient() : ApolloClient{
        if(apolloInstance != null){
            return apolloInstance!!
        }

        apolloInstance = ApolloClient
            .builder()
            .serverUrl("https://countries.trevorblades.com/").build()

        return apolloInstance!!
    }

    override suspend fun getCountries(filter: String): List<CountriesListQuery.Country>? {

        val countryFilterInput = CountryFilterInput(
            continent = Input.fromNullable(
            StringQueryOperatorInput(eq = Input.fromNullable(filter))))


        var filterInput = if(filter == ""){
            Input.absent()
        }else{
            Input.fromNullable(countryFilterInput)
        }

        val response : Response<CountriesListQuery.Data>? = try{
            apolloClient().query(CountriesListQuery(filterInput))?.await()
        }catch (e : ApolloException){
            return null
        }

        return response?.data?.countries
    }

    override suspend fun getDetailsCountry(code: String): DetailsCountryQuery.Country? {
        val response = try{
            apolloClient().query(DetailsCountryQuery(code)).await()
        }catch (e : ApolloException){
            return null
        }
        return response.data?.country
    }
}