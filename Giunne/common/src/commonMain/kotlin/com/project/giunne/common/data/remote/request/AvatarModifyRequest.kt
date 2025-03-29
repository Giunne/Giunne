package com.project.giunne.common.data.remote.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AvatarModifyRequest(
    @SerialName("classNumber")
    val classNumber: Int = 0,
    @SerialName("grade")
    val grade: Int = 0,
    @SerialName("nickName")
    val nickName: String = "",
    @SerialName("playerId")
    val playerId: Int = 0,
    @SerialName("studentNumber")
    val studentNumber: Int = 0
)