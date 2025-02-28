package com.project.giunne.common.presentation.home.student.state

import com.project.giunne.common.data.remote.response.AvatarResponse
import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.data.remote.response.PaginationInfo
import com.project.giunne.common.data.remote.response.Recreation
import com.project.giunne.common.data.util.DataThrowable

data class StudentJoinState(
    val isLoading: Boolean = false,
    val avatarInfo: AvatarResponse = AvatarResponse(),
    val recreationStudentJoinList: List<Recreation> = listOf(),
    val error: DataThrowable? = null
) {
    fun List<AvatarUserResponse>.mapToRecreation(): List<Recreation> {
        return this.map { avatar ->
            Recreation(
                id = avatar.recreationId,
                recreationCode = avatar.recreationCode,
                recreationName = avatar.recreationName,
                teacherId = avatar.teacherId,
                teacherLoginId = avatar.teacherLoginId,
                teacherName = avatar.teacherName ?: ""
            )
        }
    }
}

sealed interface StudentJoinEvent {
    data class SuccessLogin(
        val message: String
    ): StudentJoinEvent
    data class FailLogin(
        val message: String
    ): StudentJoinEvent
}