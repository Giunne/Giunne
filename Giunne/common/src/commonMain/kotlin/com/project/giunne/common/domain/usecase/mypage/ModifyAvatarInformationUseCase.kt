package com.project.giunne.common.domain.usecase.mypage

import com.project.giunne.common.data.remote.request.AvatarModifyRequest
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.MyPageRepository

class ModifyAvatarInformationUseCase(
    private val myPageRepository: MyPageRepository
) {
    suspend operator fun invoke(
        avatarModifyRequest: AvatarModifyRequest
    ): String {
        return myPageRepository
            .modifyAvatarInformation(avatarModifyRequest = avatarModifyRequest)
            .successOr("")
    }
}