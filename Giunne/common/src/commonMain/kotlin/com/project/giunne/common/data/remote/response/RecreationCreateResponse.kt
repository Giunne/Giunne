package com.project.giunne.common.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class RecreationCreateResponse(
    val id: Int = 0,
    val baseNumber: Int = 0,
    val recreationName: String = "",
    val recreationCode: String = ""
)