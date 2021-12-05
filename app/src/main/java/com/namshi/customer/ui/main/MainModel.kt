package com.namshi.customer.ui.main

import com.namshi.customer.model.NamshiWidget
import com.namshi.customer.network.response.CarouselContent
import com.namshi.customer.network.response.HomeContent
import com.namshi.customer.utils.clearAndAddAll
import io.reactivex.rxjava3.core.Observable

/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
class MainModel {

    private val repo = MainRepo()

    private var homeContent: HomeContent = HomeContent()

    fun getMainScreenContent(): Observable<HomeContent> {
        return Observable.create { emitter ->
            repo.getMainScreenContent()
                .map {
                    homeContent = it
                    if (emitter.isDisposed.not())
                        emitter.onNext(it)
                    it
                }
                .flatMap { Observable.fromIterable(it.content.filter { it2 -> it2.type == NamshiWidget.Type.carousel }) }
                .flatMap { repo.getCarouselData(it) }
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

    fun getProductList(): Observable<CarouselContent> {
        return repo.getProductList()
    }

}