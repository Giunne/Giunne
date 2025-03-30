package com.project.giunne.common.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentLikeRequest(
    @SerialName("postId") val postId: Long,
    @SerialName("rewardPoint") val rewardPoint: Long,
    @SerialName("rewardExp") val rewardExp: Long,
)