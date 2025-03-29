package com.project.giunne.common.domain.usecase.mypage

import com.project.giunne.common.data.remote.response.AvatarInformationResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.MyPageRepository

class GetAvatarInformationUseCase(
    private val myPageRepository: MyPageRepository
) {
    suspend operator fun invoke(): AvatarInformationResponse {
        return myPageRepository
            .getMyInformation()
            .successOr(AvatarInformationResponse())
    }
}