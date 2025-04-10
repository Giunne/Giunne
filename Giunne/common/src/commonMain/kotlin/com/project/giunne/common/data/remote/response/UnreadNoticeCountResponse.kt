package com.project.giunne.common.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UnreadNoticeCountResponse(
    @SerialName("count")
    val count: Int = 0
)
