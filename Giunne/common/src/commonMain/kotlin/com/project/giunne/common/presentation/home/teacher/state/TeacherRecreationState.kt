package com.project.giunne.common.presentation.home.teacher.state

import com.project.giunne.common.data.remote.response.PaginationInfo
import com.project.giunne.common.data.remote.response.Recreation
import com.project.giunne.common.data.util.DataThrowable

data class TeacherRecreationState(
    val isLoading: Boolean = false,
    val recreationList: List<Recreation> = listOf(),
    val paginationInfo: PaginationInfo = PaginationInfo(),
    val error: DataThrowable? = null
)

sealed interface TeacherRecreationEvent {
    data class ShowSnackBar(
        val message: String
    ): TeacherRecreationEvent
}