package com.namshi.customer.network

import com.namshi.customer.model.NamshiResponses
import com.namshi.customer.model.NamshiWidget
import com.namshi.customer.network.response.CarouselContent
import com.namshi.customer.network.response.HomeContent
import com.skydoves.sandwich.ApiResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


/**
 * Created by @mohamedebrahim96 on 18,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
class NamshiClient @Inject constructor(
    private val namshiService: NamshiService
) {

    suspend fun fetchHomeList(): ApiResponse<NamshiResponses> =
        namshiService.fetchHomeList()

    suspend fun fetchProductList(): ApiResponse<CarouselContent> =
        namshiService.fetchProductList()

    open fun getMainScreenContent(): Observable<HomeContent> {
        return NetworkClient.api().api1Content()
    }


    open fun getCarouselData(widget: NamshiWidget): Observable<CarouselContent> {
        return NetworkClient.api()
            .getCarouselData(widget.url)
            .map { it.url = widget.url;it }
    }
}
