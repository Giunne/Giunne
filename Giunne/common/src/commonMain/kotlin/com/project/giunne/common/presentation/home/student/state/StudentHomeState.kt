package com.project.giunne.common.presentation.home.student.state

import com.project.giunne.common.data.remote.response.AvatarResponse


data class StudentHomeState(
    val isLoading: Boolean = false,
    val avatarInfo: AvatarResponse = AvatarResponse(),
)

sealed interface StudentHomeEvent {
    data class ErrorSnackBar(val message: String) : StudentHomeEvent
}