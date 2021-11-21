package com.namshi.customer

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description


/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
@ExperimentalCoroutinesApi
class MainCoroutinesRule : TestRule, TestCoroutineScope by TestCoroutineScope(), TestWatcher() {

    val testCoroutinesDispatcher = TestCoroutineDispatcher()

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testCoroutinesDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        testCoroutinesDispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }
}
