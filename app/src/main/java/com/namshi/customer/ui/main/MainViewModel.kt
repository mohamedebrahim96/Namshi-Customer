package com.namshi.customer.ui.main

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.namshi.customer.model.NamshiResponse
import com.namshi.customer.repository.MainRepository
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by @mohamedebrahim96 on 17,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BindingViewModel() {

    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)
        private set

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
        private set

    private val homeFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    private val homeListFlow = homeFetchingIndex.flatMapLatest { page ->
        mainRepository.fetchHomeList(
            onStart = { isLoading = true },
            onComplete = { isLoading = false },
            onError = { toastMessage = it }
        )
    }

    @get:Bindable
    val homeList: List<NamshiResponse.Content> by homeListFlow.asBindingProperty(
        viewModelScope,
        emptyList()
    )

    init {
        Timber.d("init MainViewModel")
    }

}