package com.project.giunne.common.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudentSignupRequest(
    @SerialName("loginId") val loginId: String = "",
    @SerialName("password") val password: String = "",
    @SerialName("userName") val userName: String = "",
    @SerialName("nickname") val nickname: String = "",
    @SerialName("recreationCode") val recreationCode: String = "",
    @SerialName("birth") val birth: String = "",
    @SerialName("schoolId") val schoolId: Long = 0
)