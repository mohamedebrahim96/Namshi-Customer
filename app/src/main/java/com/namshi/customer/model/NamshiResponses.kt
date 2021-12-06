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
data class NamshiResponses(
    @field:Json(name = "content") var content: List<Content> = listOf()
) : Parcelable {
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Content(
        @field:Json(name = "type") var type: NamshiWidget.Type,
        @field:Json(name = "cols") val cols: Int = -1,
        @field:Json(name = "images") var images: MutableList<Image> = mutableListOf(),
        @field:Json(name = "show") val show: Int = -1,
        @field:Json(name = "title") val title: String = "",
        @field:Json(name = "height") val height: Int = -1,
        @field:Json(name = "url") val url: String = "",
    ) : Parcelable {
        @Parcelize
        @JsonClass(generateAdapter = true)
        data class Images(
            @field:Json(name = "url") var url: String,
            @field:Json(name = "width") var width: Int,
            @field:Json(name = "height") var height: Int,
            @field:Json(name = "format") var format: String
        ) : Parcelable


        enum class Type : ViewType {
            image {
                override val asInt: Int get() = 1
            },
            carousel {
                override val asInt: Int get() = 2
            },
            slider {
                override val asInt: Int get() = 3
            },
            unknown {
                override val asInt: Int get() = -1
            }
        }

        interface ViewType {
            val asInt: Int
        }
    }

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class CarouselContent(
        @field:Json(name = "images") val images: List<Image> = listOf(),
        @field:Json(name = "url") var url: String = ""
    ) : Parcelable
}

