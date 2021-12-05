package com.namshi.customer.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.namshi.customer.utils.Constants
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

/**
 * Created by @mohamedebrahim96 on 18,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
@ExperimentalSerializationApi
object NetworkClient {

    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
        .build()

    val json = Json { isLenient = true; coerceInputValues = true }
    private val jsonConverter = json.asConverterFactory("application/json".toMediaType())
    private val rxCallbacks = RxJava3CallAdapterFactory.create()

    fun api(): Api {
        val retrofit = Retrofit.Builder()
            .client(okHttp)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(jsonConverter)
            .addCallAdapterFactory(rxCallbacks)
            .build()

        return retrofit.create(Api::class.java)
    }
}