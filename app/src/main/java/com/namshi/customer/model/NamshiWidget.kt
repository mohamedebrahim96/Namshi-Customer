package com.namshi.customer.model

import com.namshi.customer.utils.Constant
import com.namshi.customer.utils.dpToPx
import kotlinx.serialization.Serializable

/**
 * Parent Data model class which holds data depending on type of [NamshiWidget.Type]
* */
@Serializable
data class NamshiWidget(
    val type: Type = Type.unknown,
    val cols: Int = -1,
    val show: Int = -1, //Carousel or Slider
    val images: MutableList<Image> = mutableListOf(),
    val title: String = Constant.EMPTY_STR, //Carousel Title
    val height: Int = -1, //Carousel Height
    val url: String = Constant.EMPTY_STR, //Carousel API URL
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