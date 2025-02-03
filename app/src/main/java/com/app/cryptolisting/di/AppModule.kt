package com.app.cryptolisting.di

import com.app.cryptolisting.core.domain_util.networking.HttpClientFactory
import com.app.cryptolisting.crypto.data.networking.RemoteCoinDataSource
import com.app.cryptolisting.crypto.domain.CoinDataSource
import com.app.cryptolisting.crypto.presentation.coin_list.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val  appModule = module {

    single { HttpClientFactory.create(CIO.create())}
    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()


    viewModelOf(::CoinListViewModel)
}