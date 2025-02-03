package com.app.cryptolisting.crypto.domain

import com.app.cryptolisting.core.domain_util.NetworkError
import com.app.cryptolisting.core.domain_util.Result
import java.time.ZonedDateTime

interface CoinDataSource {

    suspend fun getCoins() : Result<List<Coin>,NetworkError >

    suspend fun getCoinHistory(coinID: String,
                               start : ZonedDateTime,
                               end: ZonedDateTime
                               ) : Result<List<CoinPrice>,NetworkError>
}