package com.app.cryptolisting.crypto.data.networking.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.app.cryptolisting.crypto.data.networking.dto.CoinDTO
import com.app.cryptolisting.crypto.data.networking.dto.CoinPriceDto
import com.app.cryptolisting.crypto.domain.Coin
import com.app.cryptolisting.crypto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId

fun CoinDTO.toCoin ( ) : Coin{
    return  Coin(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr ?: 0.0,
        marketCapUsd = marketCapUsd
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun CoinPriceDto.toCoinPrice() : CoinPrice {
    return CoinPrice(
        priceUsd = priceUsd,
        dateTime = Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault())
    )
}