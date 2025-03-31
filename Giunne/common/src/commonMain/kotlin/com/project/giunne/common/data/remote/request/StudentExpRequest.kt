package com.project.giunne.common.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudentExpRequest(
    @SerialName("playerId")
    val playerId: Int,
    @SerialName("exp")
    val exp: Int
)
