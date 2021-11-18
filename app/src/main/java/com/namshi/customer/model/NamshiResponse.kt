package com.namshi.customer.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


/**
 * Created by @mohamedebrahim96 on 18,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
@JsonClass(generateAdapter = true)
data class NamshiResponse(
    @field:Json(name = "content") val content: List<Content>
) {
    data class Content(
        @field:Json(name = "type") var type: String,
        @field:Json(name = "cols") var cols: Int,
        @field:Json(name = "images") var images: List<Images>
    ) {
        data class Images(
            @field:Json(name = "url") var url: String,
            @field:Json(name = "width") var width: Int,
            @field:Json(name = "height") var height: Int,
            @field:Json(name = "format") var format: String
        )
    }
}