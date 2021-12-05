package com.namshi.customer.model

import com.namshi.customer.utils.dpToPx
import kotlinx.serialization.Serializable


/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
@Serializable
data class Image(
    val url: String = "",
    val width: Int = 0,
    val height: Int = 0,
    val format: Format = Format.unknown
) {

    val widthPx: Int
        get() = width.dpToPx.toInt()

    val heightPx: Int
        get() = height.dpToPx.toInt()

    @Serializable
    enum class Format { gif, image, unknown }
}