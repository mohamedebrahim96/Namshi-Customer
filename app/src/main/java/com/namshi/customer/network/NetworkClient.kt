package com.namshi.customer.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.namshi.customer.misc.json
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

/**
 * Network module for the whole app
 * Generates serializers and callback factories, loggers etc
* */
@ExperimentalSerializationApi
object NetworkClient {

    private const val BASE_URL = "https://demo8082631.mockable.io/"

    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BASIC) })
        .build()

    private val jsonConverter = json.asConverterFactory("application/json".toMediaType())
    private val rxCallbacks = RxJava3CallAdapterFactory.create()

    fun api(): Api {
        val retrofit = Retrofit.Builder()
            .client(okHttp)
            .baseUrl(BASE_URL)
            .addConverterFactory(jsonConverter)
            .addCallAdapterFactory(rxCallbacks)
            .build()

        return retrofit.create(Api::class.java)
    }
}