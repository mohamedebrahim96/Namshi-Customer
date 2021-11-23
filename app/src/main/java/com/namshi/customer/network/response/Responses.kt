package com.namshi.customer.network.response

import com.namshi.customer.model.Image
import com.namshi.customer.model.NamshiWidget
import kotlinx.serialization.Serializable

/**
 * Common wrapper class for response to understand it's state
* */
class ApiResponse<T> {
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
data class CarouselContent(val images: List<Image> = listOf(), var url: String = "")