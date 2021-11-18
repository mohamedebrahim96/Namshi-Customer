package com.namshi.customer.di

import com.namshi.customer.network.NamshiClient
import com.namshi.customer.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher


/**
 * Created by @mohamedebrahim96 on 18,November,2021
 * ShopiniWorld, Inc
 * ebrahimm131@gmail.com
 */
@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        namshiClient: NamshiClient,
        coroutineDispatcher: CoroutineDispatcher
    ): MainRepository {
        return MainRepository(namshiClient, coroutineDispatcher)
    }
}