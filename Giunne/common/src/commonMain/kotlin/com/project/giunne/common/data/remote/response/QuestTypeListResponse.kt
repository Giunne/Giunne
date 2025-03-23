package com.project.giunne.common.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestTypeListResponse(
    @SerialName("data") val data: List<QuestTypeInfo> = listOf(),
    @SerialName("paginationInfo") val paginationInfo: PaginationInfo = PaginationInfo()
)

@Serializable
data class QuestTypeInfo(
    @SerialName("id") val id: Long = 0,
    @SerialName("questName") val questName: String = "",
    @SerialName("questType") val questType: String = "",
    @SerialName("trainingType") val trainingType: String = "",
    @SerialName("cooperationType") val cooperationType: String = "",
)