package com.namshi.customer.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


/**
 * Created by @mohamedebrahim96 on 18,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class NamshiResponse(
    @field:Json(name = "content") val content: List<Content>
) : Parcelable {
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Content(
        @field:Json(name = "type") var type: String,
        @field:Json(name = "cols") var cols: Int?,
        @field:Json(name = "images") var images: List<Images>?
    ) : Parcelable {
        @Parcelize
        @JsonClass(generateAdapter = true)
        data class Images(
            @field:Json(name = "url") var url: String,
            @field:Json(name = "width") var width: Int,
            @field:Json(name = "height") var height: Int,
            @field:Json(name = "format") var format: String
        ) : Parcelable
    }
}