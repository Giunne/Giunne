package com.project.giunne.common.domain.usecase.notice

import com.project.giunne.common.data.remote.response.NoticeResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.NoticeRepository

class GetNoticeDetailUseCase(
    private val noticeRepository: NoticeRepository
) {
    suspend operator fun invoke(
        noticeId: Int,
    ): NoticeResponse {
        return noticeRepository
            .getNoticeDetail(noticeId)
            .successOr(NoticeResponse())
    }
}