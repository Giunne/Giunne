package com.project.giunne.common.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostingDetailResponse(
    @SerialName("id") val id: Int = 0,
    @SerialName("questPostProgressType") val questPostProgressType: String = "",
    @SerialName("fileUrl") val fileUrl: String = "",
    @SerialName("playerId") val playerId: Long = 0,
    @SerialName("questId") val questId: Int = 0,
    @SerialName("createTime") val createTime: String = "",
    @SerialName("updateTime") val updateTime: String = "",
    @SerialName("playerInfo") val playerInfo: AvatarUserResponse = AvatarUserResponse(),
    @SerialName("currentApproveCount") val currentApproveCount: Int = 0,
    @SerialName("questInfo") val questInfo: QuestInfo = QuestInfo(),
) {
    val isLastApprove: Boolean
        get() = currentApproveCount == questInfo.needApproveCount - 1

    val currentApproveTitle: String
        get() = "(${currentApproveCount}/${questInfo.needApproveCount})"
}