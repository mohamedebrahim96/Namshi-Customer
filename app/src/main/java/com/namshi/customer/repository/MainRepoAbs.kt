package com.namshi.customer.repository

import com.namshi.customer.model.NamshiWidget
import com.namshi.customer.network.NetworkClient
import com.namshi.customer.network.response.CarouselContent
import com.namshi.customer.network.response.HomeContent
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit


/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
abstract class MainRepoAbs {

    companion object {
        private const val TIMEOUT = 3L
        fun <T> Observable<T>.applyTimeout(): Observable<T> {
            return this.timeout(TIMEOUT, TimeUnit.SECONDS)
        }
    }

    open fun getMainScreenContent(): Observable<HomeContent> {
        return NetworkClient.api().api1Content()
    }


    open fun getCarouselData(widget: NamshiWidget): Observable<CarouselContent> {
        return NetworkClient.api()
            .getCarouselData(widget.url)
            .map { it.url = widget.url;it }
    }


    open fun getProductList(): Observable<CarouselContent> {
        return NetworkClient.api().api2List()
    }

}