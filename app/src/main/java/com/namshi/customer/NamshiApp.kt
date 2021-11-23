package com.namshi.customer

import android.app.Application
import com.namshi.customer.misc.HyperlinkedDebugTree
import dagger.hilt.android.HiltAndroidApp
import io.reactivex.rxjava3.exceptions.UndeliverableException
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import timber.log.Timber


/**
 * Created by @mohamedebrahim96 on 17,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */

@HiltAndroidApp
class NamshiApp : Application(){
    companion object {

        @get:Synchronized
        lateinit var instance: NamshiApp
            private set
    }


    override fun onCreate() {
        super.onCreate()
        instance = this


        initTimber()
        initRxErrorHandler()
    }

    private fun initTimber() {
        Timber.plant(HyperlinkedDebugTree())
    }

    private fun initRxErrorHandler() {
        //to avoid crash when undelivered exception occurs in rxjava
        RxJavaPlugins.setErrorHandler { e ->
            if (e is UndeliverableException) {
                // Merely log undeliverable exceptions
                Timber.e(e)
            } else {
                // Forward all others to current thread's uncaught exception handler
                Thread.currentThread().also { thread ->
                    thread.uncaughtExceptionHandler?.uncaughtException(thread, e)
                }
            }
        }
    }

}