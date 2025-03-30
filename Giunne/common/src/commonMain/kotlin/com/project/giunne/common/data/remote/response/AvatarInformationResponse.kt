package com.project.giunne.common.data.remote.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AvatarInformationResponse(
    @SerialName("classNumber")
    val classNumber: Int = 0,
    @SerialName("grade")
    val grade: Int = 0,
    @SerialName("schoolId")
    val schoolId: Int = 0,
    @SerialName("schoolName")
    val schoolName: String = "",
    @SerialName("nickName")
    val nickName: String = "nickName",
    @SerialName("studentNumber")
    val studentNumber: Int? = 0
) {
    val gradeAndClass: String
        get() = "${grade}학년 ${classNumber}반"
}