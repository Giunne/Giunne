package com.project.giunne.common.data.util

import io.ktor.client.plugins.HttpRequestTimeoutException
import java.net.ConnectException

internal inline fun <T> handleApi(transform: () -> BaseResponse<T>): NetworkResult<T> = try {
    val response = transform()

    if (response.code == 200) {
        NetworkResult.Success(response.value)
    } else {
        throw throwValue(response.code)
    }
} catch (e: Exception) {
    println(e.stackTraceToString())
    throw handleCommonException(e)
}

fun Throwable.asDataThrowable(): DataThrowable {
    return if (this is DataThrowable) {
        this
    } else {
        handleCommonException(this)
    }
}

private fun handleCommonException(throwable: Throwable): DataThrowable {
    return when (throwable) {
        is HttpRequestTimeoutException -> DataThrowable.TimeoutThrowable()
        is IllegalStateException -> DataThrowable.IllegalStateThrowable()
        is ConnectException -> DataThrowable.NetworkErrorThrowable()
        else -> DataThrowable.UnKnownThrowable()
    }
}

fun throwValue(code: Int): Throwable {
    val errorType = ApiErrorType.fromCode(code)
    val message = errorType?.let { ErrorMessages.getMessage(it) } ?: ErrorMessages.getMessage(ApiErrorType.UNKNOWN)

    // 에러 타입에 따라 Throwable 정의
    return handleDefaultThrowable(code)
}

// 서비스에서 지정하지 않은 Exception Handle
private fun handleDefaultThrowable(code: Int): Throwable {
    return when (code) {
        700 -> DataThrowable.IllegalStateThrowable()
        701 -> DataThrowable.NetworkErrorThrowable()
        702 -> DataThrowable.TimeoutThrowable()
        703 -> DataThrowable.UnKnownThrowable()
        else -> DataThrowable.UnKnownThrowable()
    }
}
