package com.project.giunne.common.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FCMResponse(
    @SerialName("id") val id: Long = 0,
    @SerialName("memberId") val memberId: Long = 0,
    @SerialName("token") val token: String = ""
)