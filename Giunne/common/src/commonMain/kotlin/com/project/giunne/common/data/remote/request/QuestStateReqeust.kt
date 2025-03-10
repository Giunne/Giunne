package com.project.giunne.common.data.remote.request

import kotlinx.serialization.Serializable

@Serializable
data class QuestStateRequest(
    val questStateId: Int,
    val questProgress: String
)