package com.project.giunne.common.data.remote.request

import kotlinx.serialization.Serializable

@Serializable
data class RecreationRequest(
    val baseNumber: Int,
    val recreationName: String
)