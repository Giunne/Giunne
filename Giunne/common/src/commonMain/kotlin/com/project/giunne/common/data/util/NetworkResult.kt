package com.project.giunne.common.data.util

sealed class NetworkResult<out R> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error<out T>(val data: T) : NetworkResult<T>()
    data object Loading : NetworkResult<Nothing>()
}

fun <T> NetworkResult<T>.successOr(fallback: T): T {
    return (this as? NetworkResult.Success<T>)?.data ?: fallback
}