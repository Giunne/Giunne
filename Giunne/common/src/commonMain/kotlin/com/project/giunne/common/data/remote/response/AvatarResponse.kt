package com.project.giunne.common.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class AvatarResponse(
    val accessToken: String = "",
    val accessTokenExpireTime: String = "",
    val characterNo: Int = 0,
    val exp: Int = 0,
    val grantType: String = "",
    val id: Int = 0,
    val level: Int = 0,
    val nickname: String = "",
    val point: Int = 0,
    val recreationId: Int = 0
)