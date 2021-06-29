package com.example.countries.network


import com.example.countries.CountriesListQuery
import com.example.countries.DetailsCountryQuery
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeApolloService @Inject constructor() : ApolloApiService {

    override suspend fun getCountries(filter: String): List<CountriesListQuery.Country>? {
        return when (filter) {
            "" -> getFakeCountries()
            "EU" -> getFakeEUCountries()
            else -> emptyList()
        }
    }

    override suspend fun getDetailsCountry(code: String): DetailsCountryQuery.Country? {
        return when (code) {
            "IT" -> italyDetails
            else -> franceDetails
        }
    }

    private fun getFakeCountries(): List<CountriesListQuery.Country> {
        return listOf(
            CountriesListQuery.Country(code = "IT", name = "Italia", capital = "Roma"),
            CountriesListQuery.Country(code = "fr", name = "France", capital = "Paris"),
            CountriesListQuery.Country(
                code = "us",
                name = "United States of America",
                capital = "Washington D.C."
            ),
            CountriesListQuery.Country(code = "ca", name = "Canada", capital = "Ottawa")
        )
    }

    private fun getFakeEUCountries(): List<CountriesListQuery.Country> {
        return listOf(
            CountriesListQuery.Country(code = "it", name = "Italia", capital = "Roma"),
            CountriesListQuery.Country(code = "fr", name = "France", capital = "Paris")
        )
    }

    private val italyDetails = DetailsCountryQuery.Country(
        code = "IT",
        name = "Italia",
        capital = "Roma",
        currency = "EUR",
        phone = "39",
        languages = listOf(DetailsCountryQuery.Language(name = "Italian"))
    )

    private val franceDetails = DetailsCountryQuery.Country(
        code = "FR",
        name = "France",
        capital = "Paris",
        currency = "EUR",
        phone = "33",
        languages = listOf(DetailsCountryQuery.Language(name = "French"))
    )
}