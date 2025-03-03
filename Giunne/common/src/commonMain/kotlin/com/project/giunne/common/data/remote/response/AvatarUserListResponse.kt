package com.project.giunne.common.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class AvatarUserListResponse(
    val data: List<AvatarUserResponse> = listOf(),
    val paginationInfo: PaginationInfo = PaginationInfo()
)