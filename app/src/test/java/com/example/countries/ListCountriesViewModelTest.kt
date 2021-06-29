package com.example.countries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.countries.listCountries.ListCountriesViewModel
import com.example.countries.network.ApolloApiService
import com.example.countries.testUtils.CoroutineTestRule
import com.example.countries.testUtils.getOrAwaitValue
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ListCountriesViewModelTest {

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewmodel: ListCountriesViewModel
    lateinit var apolloMock: ApolloApiService

    @Before
    fun setup(){
        apolloMock = mock()
        viewmodel = ListCountriesViewModel(apolloMock, coroutineTestRule.testDispatcherProvider)
    }

    @Test
    fun `when filter not given return all countries`() = coroutineTestRule.testDispatcher.runBlockingTest{
        whenever(apolloMock.getCountries()).thenReturn(getFakeCountries())
        viewmodel.getCountries()
        assertEquals(5, viewmodel.listCountries.getOrAwaitValue().size)
    }

    @Test
    fun `when filter EU given return EU countries`() = coroutineTestRule.testDispatcher.runBlockingTest{
        whenever(apolloMock.getCountries("EU")).thenReturn(getFakeEUCountries())
        viewmodel.getCountries("EU")
        assertEquals(3, viewmodel.listCountries.getOrAwaitValue().size)
    }



    private fun getFakeCountries(): List<CountriesListQuery.Country> {
        return listOf(
            CountriesListQuery.Country(code = "it", name = "Italia", capital = "Roma"),
            CountriesListQuery.Country(code = "srd", name = "Sardigna", capital = "Casteddu"),
            CountriesListQuery.Country(code = "fr", name = "France", capital = "Paris"),
            CountriesListQuery.Country(code = "uk", name = "United Kingdom", capital = "Liverpool"),
            CountriesListQuery.Country(code = "pt", name = "Portugal", capital = "Lisbon")
        )
    }

    private fun getFakeEUCountries(): List<CountriesListQuery.Country> {
        return listOf(
            CountriesListQuery.Country(code = "it", name = "Italia", capital = "Roma"),
            CountriesListQuery.Country(code = "fr", name = "France", capital = "Paris"),
            CountriesListQuery.Country(code = "pt", name = "Portugal", capital = "Lisbon")
        )
    }
}