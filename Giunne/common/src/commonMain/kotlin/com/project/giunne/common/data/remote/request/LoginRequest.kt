package com.project.giunne.common.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("loginId")
    val loginId: String = "",
    @SerialName("password")
    val password: String = ""
)