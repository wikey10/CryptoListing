package com.app.cryptolisting.crypto.presentation.coin_list

import com.app.cryptolisting.core.domain_util.NetworkError

sealed interface CoinListEvent {

    data class Error(val error: NetworkError) : CoinListEvent
}