package com.project.giunne.common.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SchoolListResponse(
    @SerialName("data")
    val data: List<SchoolInfo> = listOf(),
    @SerialName("paginationInfo")
    val paginationInfo: PaginationInfo = PaginationInfo(),
)