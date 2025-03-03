package com.project.giunne.common.presentation.home.student.state

import com.project.giunne.common.data.remote.response.AvatarResponse
import com.project.giunne.common.data.remote.response.AvatarUserResponse


data class StudentHomeState(
    val isLoading: Boolean = false,
    val avatarInfo: AvatarResponse = AvatarResponse(),
    val userInfo: AvatarUserResponse = AvatarUserResponse(),
)

sealed interface StudentHomeEvent {
    data class ErrorSnackBar(val message: String) : StudentHomeEvent
}