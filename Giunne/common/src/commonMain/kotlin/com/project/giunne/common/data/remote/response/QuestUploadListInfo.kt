package com.project.giunne.common.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestUploadInfo(
    @SerialName("id") val id: Int = 0,
    @SerialName("questName") val questName: String = "",
    @SerialName("isTeam") val isTeam: Boolean = false,
    @SerialName("deadline") val deadline: String = "",
    @SerialName("sortSeq") val sortSeq: Int = 0,
    @SerialName("needApproveCount") val needApproveCount: Int = 0,
    @SerialName("rewardPoint") val rewardPoint: Int = 0,
    @SerialName("rewardExp") val rewardExp: Int = 0,
    @SerialName("trainingDescription") val trainingDescription: String = "",
    @SerialName("guideUrl") val guideUrl: String = "",
    @SerialName("needLevel") val needLevel: Int = 0,
    @SerialName("maxPlayer") val maxPlayer: Int = 0,
    @SerialName("minPlayer") val minPlayer: Int = 0,
    @SerialName("questType") val questType: String = "",
    @SerialName("cooperationType") val cooperationType: String = "",
    @SerialName("trainingType") val trainingType: String = "",
    @SerialName("currentApproveCount") val currentApproveCount: Int = 0,
    @SerialName("questDescription") val questDescription: String = "",
    @SerialName("questPostInfo") val questPostInfo: List<QuestPostInfo> = listOf(),
    @SerialName("questStateInfo") val questStateInfos: QuestStateInfo = QuestStateInfo(),
    @SerialName("playerInfo") val playerInfo: AvatarUserResponse = AvatarUserResponse(),
) {
    fun getQuestTitle(): String {
        return trainingType.convertType() + " " + questName.replace(".", "단계 ")
    }
}

@Serializable
data class QuestPostInfo(
    @SerialName("id") val id: Int = 0,
    @SerialName("playerId") val playerId: Int = 0,
    @SerialName("questPostContent") val questPostContent: String = "",
    @SerialName("questPostTitle") val questPostTitle: String = "",
    @SerialName("questPostProgressType") val questPostProgressType: String = "",
)