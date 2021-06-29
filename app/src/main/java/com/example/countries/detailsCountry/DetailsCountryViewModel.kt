package com.example.countries.detailsCountry

import androidx.lifecycle.*
import com.apollographql.apollo.exception.ApolloException
import com.example.countries.DefaultDispatcherProvider
import com.example.countries.DetailsCountryQuery
import com.example.countries.DispatcherProvider
import com.example.countries.network.ApolloApiService
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsCountryViewModel @Inject constructor(
    val apolloClient: ApolloApiService,
    private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()
) : ViewModel() {

    private val _detailsCountry = MutableLiveData<DetailsCountryQuery.Country>()
    val detailsCountry: LiveData<DetailsCountryQuery.Country>
        get() = _detailsCountry

    private val _code = MutableLiveData<String>()
    val code: LiveData<String>
        get() = _code

    val countryLanguage = Transformations.map(detailsCountry) {
        val languages = mutableListOf<String>()
        languages.apply {
            detailsCountry.value?.languages?.forEach {
                languages.add(it.name!!)
            }
        }
        languages.joinToString(separator = ",\n")

    }

    fun setCode(value: String) {
        _code.value = value
    }

    fun getDetailsCountry(code: String) {
        viewModelScope.launch(dispatchers.default()) {
            val country = try {
                apolloClient.getDetailsCountry(code)
            } catch (e: ApolloException) {
                return@launch
            }
            country.let {
                _detailsCountry.postValue(it)
            }

        }
    }

}