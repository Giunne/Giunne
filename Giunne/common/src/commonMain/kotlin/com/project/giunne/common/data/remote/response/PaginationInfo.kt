package com.project.giunne.common.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginationInfo(
    @SerialName("currentPage")
    val currentPage: Int = 0,
    @SerialName("hasNextPage")
    val hasNextPage: Boolean = false,
    @SerialName("hasPreviousPage")
    val hasPreviousPage: Boolean = false,
    @SerialName("pageSize")
    val pageSize: Int = 0,
    @SerialName("totalCount")
    val totalCount: Int = 0,
    @SerialName("totalPage")
    val totalPage: Int = 0
)