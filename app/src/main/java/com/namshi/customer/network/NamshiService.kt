package com.namshi.customer.network

import com.namshi.customer.model.NamshiResponses
import com.namshi.customer.network.response.CarouselContent
import com.namshi.customer.network.response.HomeContent
import com.skydoves.sandwich.ApiResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Url


/**
 * Created by @mohamedebrahim96 on 18,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
interface NamshiService {

    @GET("content")
    suspend fun fetchHomeList(): ApiResponse<NamshiResponses>


    @GET("list")
    suspend fun fetchProductList(): ApiResponse<CarouselContent>

    @GET("content")
    fun api1Content() : Observable<HomeContent>

    @GET
    fun getCarouselData(@Url url: String): Observable<CarouselContent>

}
