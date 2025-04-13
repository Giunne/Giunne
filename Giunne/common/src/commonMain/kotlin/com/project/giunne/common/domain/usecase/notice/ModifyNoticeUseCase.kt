package com.project.giunne.common.domain.usecase.notice

import com.project.giunne.common.data.remote.request.ModifyNoticeRequest
import com.project.giunne.common.data.util.successOr
import com.project.giunne.common.domain.repository.NoticeRepository

class ModifyNoticeUseCase(
    private val noticeRepository: NoticeRepository
) {
    suspend operator fun invoke(
        modifyNoticeRequest: ModifyNoticeRequest
    ): String {
        return noticeRepository
            .modifyNotice(modifyNoticeRequest)
            .successOr("")
    }
}