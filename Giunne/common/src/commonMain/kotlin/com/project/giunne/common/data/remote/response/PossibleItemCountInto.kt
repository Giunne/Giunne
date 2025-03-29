package com.project.giunne.common.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PossibleItemCountInto(
    @SerialName("possibleCount") val possibleCount: Int = 0
)
