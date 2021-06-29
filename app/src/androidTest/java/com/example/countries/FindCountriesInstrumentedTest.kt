package com.example.countries

import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FindCountriesInstrumentedTest {

    lateinit var testScenario: ActivityScenario<MainActivity>

    @Before
    fun beforeTestsRun(){
        testScenario = ActivityScenario.launch(MainActivity::class.java)

    }

    @After
    fun afterTestRun(){
        testScenario.close()
    }

    @Test
    fun user_can_enter_a_filter(){
        onView(withId(R.id.continent_spinner)).check(matches(isDisplayed()))
        onView(withId(R.id.continent_spinner)).perform(click())
        onView(withText("Europe")).perform(click())
        onView(withText("Europe - EU")).check(matches(isDisplayed()))
    }

    @Test
    fun when_filter_is_not_given_should_show_all_countries(){
        onView(withText("Select by continent")).check(matches(isDisplayed()))
        onView(withText("Italia")).check(matches(isDisplayed()))
        onView(withText("Roma")).check(matches(isDisplayed()))
        onView(withText("France")).check(matches(isDisplayed()))
        onView(withText("Paris")).check(matches(isDisplayed()))
        onView(withText("United States of America")).check(matches(isDisplayed()))
        onView(withText("Washington D.C.")).check(matches(isDisplayed()))
        onView(withText("Canada")).check(matches(isDisplayed()))
        onView(withText("Ottawa")).check(matches(isDisplayed()))
    }

    @Test
    fun when_filter_is_given_should_show_only_countries_of_selected_continent(){
        onView(withId(R.id.continent_spinner)).perform(click())
        onView(withText("Europe")).perform(click())
        onView(withText("Italia")).check(matches(isDisplayed()))
        onView(withText("Roma")).check(matches(isDisplayed()))
        onView(withText("France")).check(matches(isDisplayed()))
        onView(withText("Paris")).check(matches(isDisplayed()))
        onView(withText("United States of America")).check(doesNotExist())
        onView(withText("Washington D.C.")).check(doesNotExist())
        onView(withText("Canada")).check(doesNotExist())
        onView(withText("Ottawa")).check(doesNotExist())
    }

    @Test
    fun when_clicking_on_a_country_in_the_list_should_show_country_details_screen(){

        onView(withText("Italia")).perform(click())
        onView(withText("Italia")).check(matches(isDisplayed()))
        onView(withText("Roma")).check(matches(isDisplayed()))
        onView(withText("EUR")).check(matches(isDisplayed()))
        onView(withText("39")).check(matches(isDisplayed()))
        onView(withText("Italian")).check(matches(isDisplayed()))
    }

    @Test
    fun when_configuration_changes_app_state_is_preserved(){
        onView(withId(R.id.continent_spinner)).perform(click())
        onView(withText("Europe")).perform(click())
        onView(withText("Italia")).check(matches(isDisplayed()))
        onView(withText("United States of America")).check(doesNotExist())

        testScenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        onView(withText("Italia")).check(matches(isDisplayed()))
        onView(withText("United States of America")).check(doesNotExist())
    }

    
}