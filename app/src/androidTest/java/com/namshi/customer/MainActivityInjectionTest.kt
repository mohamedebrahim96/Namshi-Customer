package com.namshi.customer

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.namshi.customer.ui.details.DetailsFragment
import com.namshi.customer.ui.main.MainActivity
import com.namshi.customer.ui.main.MainFragment
import com.namshi.customer.utils.SetupFragmentUtil
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainActivityInjectionTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun verifyInjection() {
        ActivityScenario.launch(MainActivity::class.java).onActivity {
            val mainFragment = MainFragment()
            SetupFragmentUtil.addFragment(
                it as AppCompatActivity,
                mainFragment,
                replace = true,
                addToBackStack = false
            )
            assertThat(mainFragment.viewModel).isNotNull()
        }
    }
}