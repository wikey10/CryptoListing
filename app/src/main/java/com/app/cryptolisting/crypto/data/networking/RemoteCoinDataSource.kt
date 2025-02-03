package com.app.cryptolisting.crypto.data.networking

import com.app.cryptolisting.core.domain_util.NetworkError
import com.app.cryptolisting.core.domain_util.Result
import com.app.cryptolisting.core.domain_util.map
import com.app.cryptolisting.core.domain_util.networking.constructURL
import com.app.cryptolisting.core.domain_util.networking.safeCall
import com.app.cryptolisting.crypto.data.networking.dto.CoinHistoryDto
import com.app.cryptolisting.crypto.data.networking.dto.CoinsResponseDto
import com.app.cryptolisting.crypto.data.networking.mappers.toCoin
import com.app.cryptolisting.crypto.data.networking.mappers.toCoinPrice
import com.app.cryptolisting.crypto.domain.Coin
import com.app.cryptolisting.crypto.domain.CoinDataSource
import com.app.cryptolisting.crypto.domain.CoinPrice


import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

class RemoteCoinDataSource (
    private val httpClient: HttpClient
) : CoinDataSource{

    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructURL("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }

    override suspend fun getCoinHistory(
        coinID: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startMillis = start
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endMillis = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

        return safeCall<CoinHistoryDto> {
            httpClient.get(
                urlString = constructURL("/assets/$coinID/history")
            ) {
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
        }
    }

//    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
//        return safeCall<CoinsResponseDto> {
//            httpClient.get(
//                urlString = constructURL("/assets")
//            )
//        }.map { response -> response.data.map { it.toCoin() } }
//      }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    override suspend fun getCoinHistory(
//        coinID: String,
//        start: ZonedDateTime,
//        end: ZonedDateTime
//    ): Result<List<CoinPrice>, NetworkError> {
//
//        val startMillis = start.withZoneSameInstant(ZoneId.of("UTC"))
//            .toInstant().toEpochMilli()
//        val endMillis = start.withZoneSameInstant(ZoneId.of("UTC"))
//            .toInstant().toEpochMilli()
//
//        return safeCall<CoinHistoryDto> {
//            httpClient.get(
//                urlString = constructURL("/assets/$coinID/history")
//            ){
//                parameter("interval","d1")
//                parameter("start", startMillis)
//                parameter("end", endMillis)
//            }
//        }.map { response -> response.data.map {
//            it.toCoinPrice()
//        } }
//    }

}