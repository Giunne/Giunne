package com.project.giunne.common.presentation.home.student.state

import com.project.giunne.common.data.remote.response.AvatarResponse
import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.data.remote.response.StudentQuestInfo
import com.project.giunne.common.data.util.DataThrowable


data class StudentHomeState(
    val isLoading: Boolean = false,
    val avatarInfo: AvatarResponse = AvatarResponse(),

    val roadmapProgressList: List<StudentQuestInfo> = listOf(),
    val error: DataThrowable? = null
)

sealed interface StudentHomeEvent {
    data class ErrorSnackBar(val message: String) : StudentHomeEvent
}