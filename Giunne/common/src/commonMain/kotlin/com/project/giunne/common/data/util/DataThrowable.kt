package com.project.giunne.common.data.util

sealed class DataThrowable(val code: Int, message: String) : Throwable(message) {
    // 각 서비스 Throwable 정의
    class ShopErrorThrowable(code: Int, message: String) : DataThrowable(code, message)
    class AuthErrorThrowable(code: Int, message: String) : DataThrowable(code, message)

    class IllegalStateThrowable : DataThrowable(700, ErrorMessages.getMessage(ApiErrorType.NETWORK_ILLEGAL_STATE_ERROR))
    class NetworkErrorThrowable : DataThrowable(701, ErrorMessages.getMessage(ApiErrorType.NETWORK_ERROR))
    class TimeoutThrowable : DataThrowable(702, ErrorMessages.getMessage(ApiErrorType.NETWORK_TIMEOUT))
    class UnKnownThrowable : DataThrowable(703, ErrorMessages.getMessage(ApiErrorType.UNKNOWN))
}