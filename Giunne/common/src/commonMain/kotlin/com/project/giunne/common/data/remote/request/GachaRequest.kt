package com.project.giunne.common.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GachaRequest(
    @SerialName("gachaTypes") val gachaTypes: String = ""
)

enum class GachaType(val text: String) {
    GENERAL("GENERAL"), PREMIUM("PREMIUM")
}