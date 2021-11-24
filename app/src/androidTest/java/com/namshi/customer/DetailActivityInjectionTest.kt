package com.namshi.customer

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat


/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class DetailActivityInjectionTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

//    @Test
//    fun verifyInjection() {
//        ActivityScenario.launch(DetailActivity::class.java).use {
//            it.moveToState(Lifecycle.State.CREATED)
//            it.onActivity { activity -> assertThat(activity.viewModel).isNotNull()
//            }
//        }
//    }
}