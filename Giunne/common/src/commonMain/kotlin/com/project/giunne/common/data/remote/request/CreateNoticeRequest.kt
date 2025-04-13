package com.project.giunne.common.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateNoticeRequest(
    @SerialName("recreationId")
    val recreationId: Int,
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String
)
