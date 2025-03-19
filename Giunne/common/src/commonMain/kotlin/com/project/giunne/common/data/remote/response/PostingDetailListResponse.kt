package com.project.giunne.common.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostingDetailListResponse(
    @SerialName("postInfoList") val postInfoList: List<PostingInfo> = listOf(),
    @SerialName("playerInfo") val playerInfo: AvatarUserResponse = AvatarUserResponse(),
    @SerialName("questInfo") val questInfo: QuestInfo = QuestInfo(),
)

@Serializable
data class PostingInfo(
    @SerialName("id") val id: Int = 0,
    @SerialName("title") val title: String = "",
    @SerialName("content") val content: String = "",
    @SerialName("questPostProgressType") val questPostProgressType: String = "",
    @SerialName("fileUrl") val fileUrl: String = "",
    @SerialName("questId") val questId: Int = 0,
    @SerialName("createTime") val createTime: String = "",
    @SerialName("updateTime") val updateTime: String = "",
)