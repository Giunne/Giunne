package com.project.giunne.common.domain.usecase.avatar

import com.project.giunne.common.data.remote.response.AvatarUserListResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.AvatarRepository

class GetUserAvatarListUseCase(
    private val avatarRepository: AvatarRepository
) {
    suspend operator fun invoke(pageIndex: Int): AvatarUserListResponse {
        return avatarRepository
            .getUserAvatarList(pageIndex)
            .successOr(AvatarUserListResponse())
    }
}