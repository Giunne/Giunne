package com.project.giunne.common.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SchoolInfo(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("rdnmadr")
    val rdnmadr: String = "",
    @SerialName("schoolNm")
    val schoolNm: String = ""
)