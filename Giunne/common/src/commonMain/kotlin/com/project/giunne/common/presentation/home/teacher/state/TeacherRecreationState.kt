package com.project.giunne.common.presentation.home.teacher.state

import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.data.remote.response.PaginationInfo
import com.project.giunne.common.data.remote.response.Recreation
import com.project.giunne.common.data.util.DataThrowable

data class TeacherRecreationState(
    val isLoading: Boolean = false,
    val recreationList: List<Recreation> = listOf(),
    val paginationInfo: PaginationInfo = PaginationInfo(),
    val error: DataThrowable? = null
) {
    fun List<AvatarUserResponse>.mapToRecreation(): List<Recreation> {
        return this.map { avatar ->
            Recreation(
                id = avatar.id,
                recreationCode = avatar.recreationCode,
                recreationName = avatar.recreationName,
                teacherId = avatar.teacherId,
                teacherLoginId = avatar.teacherLoginId,
                teacherName = avatar.teacherName ?: ""
            )
        }
    }
}

sealed interface TeacherRecreationEvent {
    data class SuccessLogin(
        val playerId: Long
    ): TeacherRecreationEvent
    data class ShowSnackBar(
        val message: String
    ): TeacherRecreationEvent
}