package com.app.cryptolisting.crypto.presentation.coin_list.models
import android.icu.text.NumberFormat
import android.provider.ContactsContract.Data
import androidx.annotation.DrawableRes
import com.app.cryptolisting.crypto.domain.Coin
import com.app.cryptolisting.crypto.domain.CoinPrice
import com.app.cryptolisting.crypto.presentation.coin_detail.DataPoint
import com.app.cryptolisting.crypto.presentation.coin_list.utils.getDrawableIdForCoin
import java.util.Locale

data class  CoinUi(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd : DisplayableNumber,
    val priceUsd : DisplayableNumber,
    val changePercent24Hr : DisplayableNumber,
    @DrawableRes val iconRes : Int,
    val coinPriceHistory : List<DataPoint> = emptyList(),

)

data class  DisplayableNumber(
    val value : Double,
    val formatted : String
)


fun Coin.toCoinUi() : CoinUi{
    return  CoinUi(
        id = id,
        name = name,
        rank = rank,
        symbol = symbol,
        priceUsd = priceUsd.toDisplayableNumber(),
        marketCapUsd = marketCapUsd.toDisplayableNumber(),
        changePercent24Hr = changePercent24Hr.toDisplayableNumber(),
        iconRes = getDrawableIdForCoin(symbol)
    )
}

fun Double.toDisplayableNumber() : DisplayableNumber{
    val formatter: NumberFormat = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return DisplayableNumber(
        value = this,
        formatted = formatter.format(this)
    )
}