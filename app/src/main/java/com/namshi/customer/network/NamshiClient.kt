package com.namshi.customer.network

import com.namshi.customer.model.NamshiResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject


/**
 * Created by @mohamedebrahim96 on 18,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
class NamshiClient @Inject constructor(
    private val namshiService: NamshiService
) {

    suspend fun fetchHomeList(): ApiResponse<NamshiResponse> =
        namshiService.fetchHomeList()
}
