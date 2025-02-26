package com.project.giunne.common.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    @SerialName("grantType")
    val grantType: String = "",
    @SerialName("accessToken")
    val accessToken: String = "",
    @SerialName("accessTokenExpireTime")
    val accessTokenExpireTime: String = "",
    @SerialName("refreshToken")
    val refreshToken: String = "",
    @SerialName("refreshTokenExpireTime")
    val refreshTokenExpireTime: String = "",
    @SerialName("role")
    val role: String = ""
)