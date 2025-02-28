package com.project.giunne.common.domain.usecase.avatar

import com.project.giunne.common.data.remote.request.AvatarLoginRequest
import com.project.giunne.common.data.remote.response.AvatarResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.AvatarRepository

class LoginRecreationUseCase(
    private val avatarRepository: AvatarRepository
) {
    suspend operator fun invoke(
        avatarLoginRequest: AvatarLoginRequest
    ): AvatarResponse {
        return avatarRepository
            .loginRecreation(avatarLoginRequest)
            .successOr(AvatarResponse())
    }
}