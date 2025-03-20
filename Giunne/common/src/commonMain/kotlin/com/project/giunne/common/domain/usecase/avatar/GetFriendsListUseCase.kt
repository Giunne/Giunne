package com.project.giunne.common.domain.usecase.avatar

import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.AvatarRepository

class GetFriendsListUseCase(
    private val avatarRepository: AvatarRepository
) {
    suspend operator fun invoke(recreationId: Long): List<AvatarUserResponse> {
        return avatarRepository
            .getRecreationAvatarList(recreationId = recreationId)
            .successOr(listOf())
    }
}