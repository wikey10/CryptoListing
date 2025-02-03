package com.app.cryptolisting.crypto.presentation.coin_list

import com.app.cryptolisting.crypto.presentation.coin_list.models.CoinUi

sealed interface CoinListAction{

    data class OnClick(val coinUi: CoinUi) : CoinListAction

//    data object OnRefresh : CoinListAction

}