package com.namshi.customer

import android.app.Application
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
class NamshiApp : Application()