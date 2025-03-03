package com.project.giunne.common.presentation.home.teacher.state

import com.project.giunne.common.data.remote.response.RecreationCreateResponse
import com.project.giunne.common.data.util.DataThrowable

data class TeacherHomeState(
    val isLoading: Boolean = false,
    val showDialog: Boolean = false,
    val createRecreationResult: RecreationCreateResponse = RecreationCreateResponse(),
    val error: DataThrowable? = null
)

sealed interface TeacherHomeEvent {
    data class ShowSnackBar(
        val message: String
    ): TeacherHomeEvent
}