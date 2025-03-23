package com.project.giunne.common.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentRequest(
    @SerialName("postId") val postId: Long = 0,
    @SerialName("content") val content: String = ""
)