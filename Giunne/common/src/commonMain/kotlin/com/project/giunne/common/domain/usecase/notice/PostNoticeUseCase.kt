package com.project.giunne.common.domain.usecase.notice

import com.project.giunne.common.data.remote.request.CreateNoticeRequest
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.NoticeRepository

class PostNoticeUseCase(
    private val noticeRepository: NoticeRepository
) {
    suspend operator fun invoke(
        createNoticeRequest: CreateNoticeRequest
    ): String {
        return noticeRepository
            .postNewNotice(createNoticeRequest)
            .successOr("")
    }
}