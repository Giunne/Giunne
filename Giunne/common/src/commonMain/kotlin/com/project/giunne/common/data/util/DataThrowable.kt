package com.project.giunne.common.data.util

sealed class DataThrowable(val code: String, message: String) : Throwable(message) {
    // 각 서비스 Throwable 정의
    class ShopErrorThrowable(code: String, message: String) : DataThrowable(code, message)

    class IllegalStateThrowable : DataThrowable("U1", ErrorMessages.getMessage(ApiErrorType.NETWORK_ILLEGAL_STATE_ERROR))
    class NetworkErrorThrowable : DataThrowable("U2", ErrorMessages.getMessage(ApiErrorType.NETWORK_ERROR))
    class TimeoutThrowable : DataThrowable("U3", ErrorMessages.getMessage(ApiErrorType.NETWORK_TIMEOUT))
    class UnKnownThrowable : DataThrowable("U4", ErrorMessages.getMessage(ApiErrorType.UNKNOWN))
}