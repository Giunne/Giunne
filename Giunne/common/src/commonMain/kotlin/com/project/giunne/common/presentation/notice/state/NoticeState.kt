package com.project.giunne.common.presentation.notice.state

import com.project.giunne.common.data.remote.response.NoticeResponse
import com.project.giunne.common.data.remote.response.PaginationInfo
import com.project.giunne.common.data.util.DataThrowable

data class NoticeState(
    val isLoading: Boolean = false,
    val isOpen: Boolean = false,
    val isCreateNoticeDialog: Boolean = false,
    val error: DataThrowable? = null,
    val noticeList: List<NoticeResponse> = listOf(),
    val paginationInfo: PaginationInfo = PaginationInfo()
)

sealed interface NoticeEvent {
    data class CreateNewNotice(
        val message: String
    ): NoticeEvent
}
