package com.project.giunne.common.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IDExistInfo(
    @SerialName("isPresent") val isPresent: Boolean = false
)
