package com.project.giunne.common.presentation.home.student.state

import com.project.giunne.common.data.remote.response.PaginationInfo
import com.project.giunne.common.data.remote.response.Recreation
import com.project.giunne.common.data.util.DataThrowable

data class SearchRoadmapState(
    val isLoading: Boolean = false,
    val searchRecreationList: List<Recreation> = listOf(),
    val paginationInfo: PaginationInfo = PaginationInfo(),
    val error: DataThrowable? = null
)

sealed interface SearchRoadmapEvent