package com.project.giunne.common.domain.usecase.notice

import com.project.giunne.common.data.remote.response.NoticeListResponse
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.NoticeRepository

class GetNoticeListUseCase(
    private val noticeRepository: NoticeRepository
) {
    suspend operator fun invoke(
        pageIndex: Int,
        recreationId: Int
    ): NoticeListResponse {
        return noticeRepository
            .getNoticeList(pageIndex, recreationId)
            .successOr(NoticeListResponse())
    }
}