package com.namshi.customer.ui.details

import androidx.lifecycle.MutableLiveData
import com.namshi.customer.network.response.CarouselContent
import com.namshi.customer.network.response.HomeContent
import com.namshi.customer.network.response.NamshiResponse
import com.namshi.customer.repository.MainRepository
import com.namshi.customer.ui.main.MainModel
import com.namshi.customer.utils.plusAssign
import com.skydoves.bindables.BindingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by @mohamedebrahim96 on 17,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BindingViewModel() {


    private val productList: NamshiResponse<CarouselContent> = NamshiResponse()
    val productListLiveData: MutableLiveData<NamshiResponse<CarouselContent>> =
        MutableLiveData(NamshiResponse())

    private val model: MainModel = MainModel()
    private val subscriptions = CompositeDisposable()


    fun getProductList() {
        subscriptions += model.getProductList()
            .doOnSubscribe {
                productList.isLoading = true
                productListLiveData.postValue(productList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                productList.isLoading = false
                productList.data = it
                productListLiveData.postValue(productList)
            }, {
                Timber.e(it)
                productList.isLoading = false
                productList.exception = it as Exception?
                productListLiveData.postValue(productList)
            })
    }

    fun refreshProductScreen() = getProductList()

}