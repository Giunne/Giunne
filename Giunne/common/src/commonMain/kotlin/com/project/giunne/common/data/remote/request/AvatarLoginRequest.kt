package com.project.giunne.common.data.remote.request

import kotlinx.serialization.Serializable

@Serializable
data class AvatarLoginRequest(
    val playerId: Int
)