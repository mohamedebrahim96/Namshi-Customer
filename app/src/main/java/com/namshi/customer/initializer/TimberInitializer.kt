package com.namshi.customer.initializer

import android.content.Context
import androidx.startup.Initializer
import com.namshi.customer.BuildConfig
import timber.log.Timber


/**
 * Created by @mohamedebrahim96 on 23,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
class TimberInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
