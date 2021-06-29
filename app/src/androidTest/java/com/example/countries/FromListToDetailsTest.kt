package com.example.countries


import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.testing.withFragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.countries.listCountries.ListCountriesFragment
import com.example.countries.listCountries.ListCountriesFragmentDirections
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class FromListToDetailsTest {

    @Test
    fun when_click_on_country_navigate_to_detail_fragment() {
        val scenario =
            launchFragmentInContainer<ListCountriesFragment>(Bundle(), R.style.Theme_AppCompat)
        val navController: NavController = mock(NavController::class.java)

        scenario.withFragment {
            Navigation.setViewNavController(this.requireView(), navController)
        }

        onView(withId(R.id.countriesList))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(withText("Italia")), click()
                )
            )

        verify(navController).navigate(
            ListCountriesFragmentDirections.actionListCountriesFragmentToDetailsCountryFragment("IT")
        )
    }

}