package com.project.giunne.common.domain.usecase.avatar

import com.project.giunne.common.data.remote.request.AvatarCreateRequest
import com.project.giunne.common.data.remote.response.AvatarResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.AvatarRepository

class CreateAvatarUseCase(
    private val avatarRepository: AvatarRepository
) {
    suspend operator fun invoke(
        avatarCreateRequest: AvatarCreateRequest
    ): AvatarResponse {
        return avatarRepository
            .createAvatar(avatarCreateRequest)
            .successOr(AvatarResponse())
    }
}