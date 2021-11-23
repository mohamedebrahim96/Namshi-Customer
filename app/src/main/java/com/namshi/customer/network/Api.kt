package com.namshi.customer.network

import com.namshi.customer.network.response.CarouselContent
import com.namshi.customer.network.response.HomeContent
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Behaviour of Mockable.io API
* */
interface Api {

    @GET("content")
    fun api1Content() : Observable<HomeContent>

    @GET("list")
    fun api2List() : Observable<CarouselContent>

    @GET
    fun getCarouselData(@Url url: String): Observable<CarouselContent>
}