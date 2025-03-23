package com.project.giunne.common.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentListResponse(
    @SerialName("data") val data: List<CommentInfo> = listOf(),
    @SerialName("paginationInfo") val paginationInfo: PaginationInfo = PaginationInfo()
)

@Serializable
data class CommentInfo(
    @SerialName("id") val id: Int = 0,
    @SerialName("content") val content: String = "",
    @SerialName("createTime") val createTime: String = "",
    @SerialName("updateTime") val updateTime: String = "",
    @SerialName("likeCount") val likeCount: Int = 0,
    @SerialName("playerInfo") val playerInfo: AvatarUserResponse = AvatarUserResponse(),
    @SerialName("likedByMe") val likedByMe: Boolean = false,
)