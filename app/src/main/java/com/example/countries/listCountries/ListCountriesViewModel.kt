package com.example.countries.listCountries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.exception.ApolloException
import com.example.countries.network.ApolloApiService
import com.example.countries.CountriesListQuery
import com.example.countries.DefaultDispatcherProvider
import com.example.countries.DispatcherProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListCountriesViewModel @Inject constructor(
    val apolloClient: ApolloApiService,
    private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()
) : ViewModel() {


    private val _listCountries = MutableLiveData<List<CountriesListQuery.Country>>()

    val listCountries : LiveData<List<CountriesListQuery.Country>>
        get() = _listCountries

    init {
        getCountries()
    }

    fun getCountries(filter: String = "") {
        viewModelScope.launch(dispatchers.default()) {
            val countries = try {
                apolloClient.getCountries(filter)
            } catch (e: ApolloException) {
                return@launch
            }

            _listCountries.postValue(countries)

        }


    }
}