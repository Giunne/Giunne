package com.project.giunne.common.domain.usecase.avatar

import com.project.giunne.common.data.remote.request.StudentExpRequest
import com.project.giunne.common.data.remote.request.StudentPointRequest
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.AvatarRepository

class ModifyStudentExpUseCase(
    private val avatarRepository: AvatarRepository
) {
    suspend operator fun invoke(
        studentExpRequest: StudentExpRequest
    ): String {
        return avatarRepository
            .modifyStudentExp(studentExpRequest)
            .successOr("")
    }
}