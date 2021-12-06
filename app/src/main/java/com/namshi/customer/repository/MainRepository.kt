package com.namshi.customer.repository

import androidx.annotation.WorkerThread
import com.namshi.customer.model.NamshiWidget
import com.namshi.customer.network.NamshiClient
import com.namshi.customer.network.response.HomeContent
import com.namshi.customer.utils.clearAndAddAll
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import io.reactivex.rxjava3.core.Observable
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
            emit(data.content)
        }
            .onError {
                onError(message())
            }
            .onException { onError(message) }

    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)


    private var homeContent: HomeContent = HomeContent()

    fun getMainScreenContent(): Observable<HomeContent> {
        return Observable.create { emitter ->
            namshiClient.getMainScreenContent()
                .map {
                    homeContent = it
                    if (emitter.isDisposed.not())
                        emitter.onNext(it)
                    it
                }
                .flatMap { Observable.fromIterable(it.content.filter { it2 -> it2.type == NamshiWidget.Type.carousel }) }
                .flatMap { namshiClient.getCarouselData(it) }
                .map { widget ->
                    val data = homeContent.content.find { it.url == widget.url }
                    data?.images?.clearAndAddAll(widget.images)
                    if (emitter.isDisposed.not())
                        emitter.onNext(homeContent)
                }
                .subscribe({
                    if (emitter.isDisposed.not()) emitter.onComplete()
                }, emitter::onError)
        }
    }
}
