package com.project.giunne.common.data.remote.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GradeStudentRequest(
    @SerialName("hasExtraPoints")
    val hasExtraPoints: Boolean = false,
    @SerialName("isPass")
    val isPass: Boolean = false,
    @SerialName("questPostId")
    val questPostId: Long = 0,
    @SerialName("starPoint")
    val starPoint: Int = 0
)