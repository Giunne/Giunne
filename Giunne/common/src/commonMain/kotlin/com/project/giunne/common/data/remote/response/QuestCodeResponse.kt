package com.project.giunne.common.data.remote.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestCodeResponse(
    @SerialName("questProgress")
    val questProgress: List<QuestProgress> = listOf()
)

@Serializable
data class QuestProgress(
    @SerialName("code")
    val code: String = "",
    @SerialName("title")
    val title: String = ""
)