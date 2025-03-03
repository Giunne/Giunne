package com.project.giunne.common.data.remote.request

import kotlinx.serialization.Serializable

@Serializable
data class AvatarCreateRequest(
    val recreationId: Int,
    val nickName: String,
    val grade: Int,
    val characterNo: Int,
    val classNumber: Int,
    val studentNumber: Int = 0
)