package com.example.countries.testUtils

import com.example.countries.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import javax.inject.Inject

@ExperimentalCoroutinesApi
class TestDispatcherProvider @Inject constructor() :
    DispatcherProvider {
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    override fun default(): CoroutineDispatcher = testDispatcher
    override fun io(): CoroutineDispatcher = testDispatcher
    override fun main(): CoroutineDispatcher = testDispatcher
    override fun unconfined(): CoroutineDispatcher = testDispatcher
}