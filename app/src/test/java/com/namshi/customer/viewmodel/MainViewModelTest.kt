package com.namshi.customer.viewmodel

import app.cash.turbine.test

import com.namshi.customer.MainCoroutinesRule
import com.namshi.customer.network.NamshiClient
import com.namshi.customer.network.NamshiService
import com.namshi.customer.repository.MainRepository
import com.namshi.customer.ui.main.MainViewModel
import com.namshi.customer.utils.MockUtil
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.DurationUnit
import kotlin.time.toDuration


/**
 * Created by @mohamedebrahim96 on 21,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
class MainViewModelTest {

//    private lateinit var viewModel: MainViewModel
//    private lateinit var mainRepository: MainRepository
//    private val namshiService: NamshiService = mock()
//    private val namshiClient: NamshiClient = NamshiClient(namshiService)
//
//    @get:Rule
//    var coroutinesRule = MainCoroutinesRule()
//
//    @Before
//    fun setup() {
//        mainRepository = MainRepository(namshiClient, Dispatchers.IO)
//        viewModel = MainViewModel(mainRepository)
//    }
//
//    @Test
//    fun fetchContentListTest() = runBlocking {
//        val mockData = MockUtil.mockContentList()
//
//        val fetchedDataFlow = mainRepository.fetchHomeList(
//            onStart = {},
//            onComplete = {},
//            onError = {}
//        ).test(2.toDuration(DurationUnit.SECONDS)) {
//            val item = awaitItem()
//            Assert.assertEquals(item[0].type, "image")
//            Assert.assertEquals(item[0].cols, 1)
//            Assert.assertEquals(item, MockUtil.mockContentList())
//            awaitComplete()
//        }
//
//
//        fetchedDataFlow.apply {
//            // runBlocking should return Unit
//        }
//    }
}