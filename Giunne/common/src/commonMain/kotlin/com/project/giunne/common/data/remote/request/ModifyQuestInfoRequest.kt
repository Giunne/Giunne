package com.project.giunne.common.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ModifyQuestInfoRequest(
    @SerialName("id") val id: Int = 0,
    @SerialName("questDescription") val questDescription: String = "",
    @SerialName("trainingDescription") val trainingDescription: String = "",
    @SerialName("rewardPoint") val rewardPoint: Long = 0,
    @SerialName("rewardExp") val rewardExp: Long = 0,
    @SerialName("guideUrl") val guideUrl: String = "",
)