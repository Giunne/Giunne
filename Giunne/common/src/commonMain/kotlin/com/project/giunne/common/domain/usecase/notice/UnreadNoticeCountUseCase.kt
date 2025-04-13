package com.project.giunne.common.domain.usecase.notice

import com.project.giunne.common.data.remote.response.UnreadNoticeCountResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.NoticeRepository

class UnreadNoticeCountUseCase(
    private val noticeRepository: NoticeRepository
) {
    suspend operator fun invoke(): UnreadNoticeCountResponse {
        return noticeRepository
            .unreadNoticeCount()
            .successOr(UnreadNoticeCountResponse())
    }
}