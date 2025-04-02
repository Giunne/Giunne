package com.project.giunne.common.domain.usecase.avatar

import com.project.giunne.common.data.remote.request.PasswordChangeRequest
import com.project.giunne.common.data.remote.request.PasswordResetRequest
import com.project.giunne.common.data.remote.request.StudentPointRequest
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.AvatarRepository

class ChangeStudentPasswordUseCase(
    private val avatarRepository: AvatarRepository
) {
    suspend operator fun invoke(
        passwordChangeRequest: PasswordChangeRequest
    ): String {
        return avatarRepository
            .changeStudentPassword(passwordChangeRequest)
            .successOr("")
    }
}