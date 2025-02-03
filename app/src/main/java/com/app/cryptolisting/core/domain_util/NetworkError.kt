package com.app.cryptolisting.core.domain_util

enum class NetworkError : Error {

    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN,

}