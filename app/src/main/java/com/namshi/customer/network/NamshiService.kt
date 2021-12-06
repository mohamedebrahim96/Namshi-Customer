package com.namshi.customer.network

import com.namshi.customer.model.NamshiResponses
import com.skydoves.sandwich.ApiResponse
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
    suspend fun fetchProductList(): ApiResponse<NamshiResponses.CarouselContent>

    @GET
    suspend fun getCarouselData(@Url url: String): ApiResponse<NamshiResponses.CarouselContent>

}
