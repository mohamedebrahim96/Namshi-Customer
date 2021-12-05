package com.namshi.customer.model

import com.namshi.customer.utils.dpToPx
import kotlinx.serialization.Serializable


/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
@Serializable
data class NamshiWidget(
    val type: Type = Type.unknown,
    val cols: Int = -1,
    val show: Int = -1,
    val images: MutableList<Image> = mutableListOf(),
    val title: String = "",
    val height: Int = -1,
    val url: String = "",
) {

    val heightPx: Int
        get() = height.dpToPx.toInt()

    @Serializable
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