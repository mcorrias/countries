package com.example.countries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.countries.detailsCountry.DetailsCountryViewModel
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
class DetailsCountryViewModelTest {

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: DetailsCountryViewModel
    lateinit var apolloMock: ApolloApiService

    @Before
    fun setup(){
        apolloMock = mock()
        viewModel = DetailsCountryViewModel(apolloMock, coroutineTestRule.testDispatcherProvider)
    }

    @Test
    fun `when country code is given return selected country details`() = coroutineTestRule.testDispatcher.runBlockingTest {
        whenever(apolloMock.getDetailsCountry("IT")).thenReturn(italyDetails)
        viewModel.getDetailsCountry("IT")
        val country = viewModel.detailsCountry.getOrAwaitValue()
        assertEquals("Italia", country.name)
        assertEquals("Roma", country.capital)
        assertEquals("EUR", country.currency)
        assertEquals("39", country.phone)
        assertEquals("Italian", country.languages[0].name)

    }

    private val italyDetails = DetailsCountryQuery.Country(
        code = "IT",
        name = "Italia",
        capital = "Roma",
        currency = "EUR",
        phone = "39",
        languages = listOf(DetailsCountryQuery.Language(name="Italian"))
    )
}