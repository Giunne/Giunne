package com.project.giunne.common.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeacherSignupRequest(
    @SerialName("loginId") val loginId: String = "",
    @SerialName("password") val password: String = "",
    @SerialName("userName") val userName: String = "",
    @SerialName("nickname") val nickname: String = "",
    @SerialName("birth") val birth: String = "",
    @SerialName("phone") val phone: String = "",
    @SerialName("email") val email: String = "",
    @SerialName("schoolId") val schoolId: Long = 0
)