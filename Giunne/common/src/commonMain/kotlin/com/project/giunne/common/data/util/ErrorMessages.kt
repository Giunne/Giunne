package com.project.giunne.common.data.util

import com.project.giunne.Res
import com.project.giunne.duplicate_id_error
import com.project.giunne.network_error_message
import com.project.giunne.network_illegal_state_error_message
import com.project.giunne.network_timeout_message
import com.project.giunne.unknown_error_message
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString

enum class ApiErrorType(
    val code: Int,
    val messageResId: StringResource
) {
    // Error Type 정의
    DUPLICATE_ID(400, Res.string.duplicate_id_error),

    NETWORK_ILLEGAL_STATE_ERROR(700, Res.string.network_illegal_state_error_message),
    NETWORK_ERROR(701, Res.string.network_error_message),
    NETWORK_TIMEOUT(702, Res.string.network_timeout_message),
    UNKNOWN(703, Res.string.unknown_error_message);

    companion object {
        fun fromCode(code: Int): ApiErrorType? = entries.find { it.code == code }
    }
}

object ErrorMessages {
    var message: Map<ApiErrorType, String> = emptyMap()
        private set

    suspend fun init() {
        message = ApiErrorType.entries.associateWith {
            getString(it.messageResId)
        }
    }

    fun getMessage(errorType: ApiErrorType): String {
        return message[errorType] ?: message[ApiErrorType.UNKNOWN].toString()
    }
}