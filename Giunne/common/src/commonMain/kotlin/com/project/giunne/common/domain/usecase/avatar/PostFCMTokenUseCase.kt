package com.project.giunne.common.domain.usecase.avatar

import com.project.giunne.common.data.remote.request.AvatarCreateRequest
import com.project.giunne.common.data.remote.request.FCMRequest
import com.project.giunne.common.data.remote.response.AvatarResponse
import com.project.giunne.common.data.remote.response.FCMResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.AvatarRepository

class PostFCMTokenUseCase(
    private val avatarRepository: AvatarRepository
) {
    suspend operator fun invoke(
        fcmRequest: FCMRequest
    ): String {
        return avatarRepository
            .postFCMToken(fcmRequest)
            .successOr("")
    }
}