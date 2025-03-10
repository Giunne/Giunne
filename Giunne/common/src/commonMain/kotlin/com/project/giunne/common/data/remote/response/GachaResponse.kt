package com.project.giunne.common.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class GachaResponse(
    val code: String = "",
    val codeName: String = "",
    val price: Int = 0,
    val itemGradeMap: Map<String, Int> = mapOf(),
    val imageList: List<String> = listOf(),
)