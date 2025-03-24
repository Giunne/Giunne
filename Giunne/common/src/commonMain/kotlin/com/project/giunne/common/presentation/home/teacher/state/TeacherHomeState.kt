package com.project.giunne.common.presentation.home.teacher.state

import com.project.giunne.common.data.remote.response.QuestUploadInfo
import com.project.giunne.common.data.remote.response.Recreation
import com.project.giunne.common.data.remote.response.RecreationCreateResponse
import com.project.giunne.common.data.util.DataThrowable

data class TeacherHomeState(
    val isLoading: Boolean = false,
    val showDialog: Boolean = false,
    val createRecreationResult: RecreationCreateResponse = RecreationCreateResponse(),
    val recreation: Recreation = Recreation(),
    val certWaitList: List<QuestUploadInfo> = listOf(),
    val error: DataThrowable? = null
)

sealed interface TeacherHomeEvent {
    data class CreateAvatar(
        val recreationId: Int
    ): TeacherHomeEvent
    data class ShowSnackBar(
        val message: String
    ): TeacherHomeEvent
}