package com.project.giunne.common.domain.usecase.avatar

import com.project.giunne.common.data.remote.response.FCMResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.AvatarRepository

class GetFCMTokenListUseCase(
    private val avatarRepository: AvatarRepository
) {
    suspend operator fun invoke(
        memberId: Long
    ): List<FCMResponse> {
        return avatarRepository
            .getFCMTokenList(memberId)
            .successOr(listOf())
    }
}