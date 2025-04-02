package com.project.giunne.common.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PasswordResetRequest(
    @SerialName("avatarId") val avatarId: Long = 0,
    @SerialName("password") val password: String = "",
)

@Serializable
data class PasswordChangeRequest(
    @SerialName("password") val password: String = "",
)