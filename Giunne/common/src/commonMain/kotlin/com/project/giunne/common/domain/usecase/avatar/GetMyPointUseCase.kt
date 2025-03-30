package com.project.giunne.common.domain.usecase.avatar

import com.project.giunne.common.data.remote.response.MyPointInfo
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.AvatarRepository

class GetMyPointUseCase(
    private val avatarRepository: AvatarRepository
) {
    suspend operator fun invoke(): MyPointInfo {
        return avatarRepository
            .getPointInfo()
            .successOr(MyPointInfo())
    }
}