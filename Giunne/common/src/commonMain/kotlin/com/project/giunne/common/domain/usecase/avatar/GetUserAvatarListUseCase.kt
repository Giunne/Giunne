package com.project.giunne.common.domain.usecase.avatar

import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.AvatarRepository

class GetUserAvatarListUseCase(
    private val avatarRepository: AvatarRepository
) {
    suspend operator fun invoke(): List<AvatarUserResponse> {
        return avatarRepository
            .getUserAvatarList()
            .successOr(listOf())
    }
}