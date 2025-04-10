package com.project.giunne.common.presentation.notice.state

import com.project.giunne.common.data.remote.response.NoticeResponse
import com.project.giunne.common.data.remote.response.PaginationInfo
import com.project.giunne.common.data.util.DataThrowable

data class NoticeState(
    val isLoading: Boolean = false,
    val isOpen: Boolean = false,
    val isCreateNoticeDialog: Boolean = false,
    val isNoticeDetailDialog: Boolean = false,
    val error: DataThrowable? = null,
    val noticeList: List<NoticeResponse> = listOf(),
    val currentNotice: NoticeResponse = NoticeResponse(),
    val paginationInfo: PaginationInfo = PaginationInfo()
)

sealed interface NoticeEvent {
    data class CreateNewNotice(
        val message: String
    ): NoticeEvent
    data class ModifyNotice(
        val noticeId: Int,
        val message: String
    ): NoticeEvent
    data class DeleteNotice(
        val message: String
    ): NoticeEvent
    data object ReadNotice: NoticeEvent
}
