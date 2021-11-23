package com.namshi.customer.model

import com.namshi.customer.utils.Constant
import com.namshi.customer.utils.dpToPx
import kotlinx.serialization.Serializable

/**
 * Image data class type which represents an Image from API response
* */
@Serializable
data class Image(
    val url: String = Constant.EMPTY_STR,
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