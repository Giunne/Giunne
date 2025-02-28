package com.project.giunne.common.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class RecreationStudentJoinResponse(
    val data: List<Recreation> = listOf(),
    val paginationInfo: PaginationInfo = PaginationInfo()
)
