package com.namshi.customer.model

import android.os.Parcelable
import com.namshi.customer.utils.dpToPx
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class Image(
    @field:Json(name = "url") val url: String = "",
    @field:Json(name = "width") val width: Int = 0,
    @field:Json(name = "height") val height: Int = 0,
    @field:Json(name = "format") val format: Format = Format.unknown
) : Parcelable {

    val widthPx: Int
        get() = width.dpToPx.toInt()

    val heightPx: Int
        get() = height.dpToPx.toInt()

    enum class Format { gif, image, unknown }
}