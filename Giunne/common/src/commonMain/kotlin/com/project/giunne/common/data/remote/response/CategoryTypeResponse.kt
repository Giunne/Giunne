package com.project.giunne.common.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class CategoryTypeResponse(
    val id: Long = 0,
    val categoryName: String = "",
    val isRoot: Boolean = true,
    val isLeaf: Boolean = true
)