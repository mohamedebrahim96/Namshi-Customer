package com.namshi.customer.network.response

import android.os.Parcelable
import com.namshi.customer.model.Image
import com.namshi.customer.model.NamshiWidget
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
class NamshiResponse<T> {
    var isLoading: Boolean = false
    var exception: Exception? = null
    var data: T? = null
}

/**
 * API 1 Response
 * */
@Serializable
data class HomeContent(val content: List<NamshiWidget> = listOf())

/**
 * API 2, 3, 4 Response
 * */
@Serializable
@Parcelize
@JsonClass(generateAdapter = true)
data class CarouselContent(
    @field:Json(name = "images") val images: List<Image> = listOf(),
    @field:Json(name = "url") var url: String = ""
) : Parcelable