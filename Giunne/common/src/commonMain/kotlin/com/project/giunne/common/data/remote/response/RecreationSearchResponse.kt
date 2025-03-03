package com.project.giunne.common.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class RecreationSearchResponse(
    val data: List<Recreation> = listOf(),
    val paginationInfo: PaginationInfo = PaginationInfo()
)

@Serializable
data class Recreation(
    val id: Int = 0,
    val recreationCode: String = "",
    val recreationName: String = "",
    val teacherId: Int = 0,
    val teacherLoginId: String = "",
    val teacherName: String = ""
)
