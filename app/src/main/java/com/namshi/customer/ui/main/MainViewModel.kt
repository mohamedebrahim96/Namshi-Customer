package com.namshi.customer.ui.main

import androidx.lifecycle.MutableLiveData
import com.namshi.customer.network.response.HomeContent
import com.namshi.customer.network.response.NamshiResponse
import com.namshi.customer.repository.MainRepository
import com.namshi.customer.utils.plusAssign
import com.skydoves.bindables.BindingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BindingViewModel() {

    private val homeContent: NamshiResponse<HomeContent> = NamshiResponse()
    val homeContentLiveData: MutableLiveData<NamshiResponse<HomeContent>> =
        MutableLiveData(NamshiResponse())

    private val model: MainModel = MainModel()
    private val subscriptions = CompositeDisposable()

    init {
        fetchInitialData()
    }

    fun refreshMainScreen() = fetchInitialData()

    private fun fetchInitialData() {
        subscriptions += model.getMainScreenContent()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                homeContent.isLoading = true
                homeContentLiveData.postValue(homeContent)
            }
            .subscribe({
                homeContent.isLoading = false
                homeContent.data = it
                homeContent.exception = null
                homeContentLiveData.postValue(homeContent)
            }, {
                Timber.e(it)
                homeContent.isLoading = false
                homeContent.exception = it as Exception?
                homeContentLiveData.postValue(homeContent)
            })
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

}