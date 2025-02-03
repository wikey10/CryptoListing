package com.app.cryptolisting.crypto.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinsResponseDto(
    val data : List<CoinDTO>

)
