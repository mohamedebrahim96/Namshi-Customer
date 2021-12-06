package com.namshi.customer.repository

import androidx.annotation.WorkerThread
import com.namshi.customer.model.Image
import com.namshi.customer.model.NamshiWidget
import com.namshi.customer.network.NamshiClient
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */

class MainRepository @Inject constructor(
    private val namshiClient: NamshiClient,
    private val ioDispatcher: CoroutineDispatcher
) : Repository {

    @WorkerThread
    fun fetchHomeList(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        val response = namshiClient.fetchHomeList()
        response.suspendOnSuccess {
            emit(data.content.map {
                if (it.type == NamshiWidget.Type.carousel) {
                    val response = namshiClient.getCarouselData(it.url)
                    var carouselList = mutableListOf<Image>()
                    response.suspendOnSuccess {
                        carouselList = data.images.toMutableList()
                    }
                    return@map NamshiWidget(
                        type = it.type,
                        cols = it.cols,
                        show = it.show,
                        images = carouselList,
                        title = it.title,
                        height = it.height,
                        url = it.url
                    )
                } else {
                    return@map NamshiWidget(
                        type = it.type,
                        cols = it.cols,
                        show = it.show,
                        images = it.images,
                        title = it.title,
                        height = it.height,
                        url = it.url
                    )
                }
            })
        }
            .onError {
                onError(message())
            }
            .onException { onError(message) }

    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)

}
