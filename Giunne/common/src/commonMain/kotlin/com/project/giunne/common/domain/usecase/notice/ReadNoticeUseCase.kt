package com.project.giunne.common.domain.usecase.notice

import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.NoticeRepository

class ReadNoticeUseCase(
    private val noticeRepository: NoticeRepository
) {
    suspend operator fun invoke(
        noticeId: Int
    ): String {
        return noticeRepository
            .readNotice(noticeId)
            .successOr("")
    }
}