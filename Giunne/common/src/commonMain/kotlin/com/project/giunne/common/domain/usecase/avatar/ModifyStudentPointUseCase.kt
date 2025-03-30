package com.project.giunne.common.domain.usecase.avatar

import com.project.giunne.common.data.remote.request.StudentPointRequest
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.AvatarRepository

class ModifyStudentPointUseCase(
    private val avatarRepository: AvatarRepository
) {
    suspend operator fun invoke(
        studentPointRequest: StudentPointRequest
    ): String {
        return avatarRepository
            .modifyStudentPoint(studentPointRequest)
            .successOr("")
    }
}