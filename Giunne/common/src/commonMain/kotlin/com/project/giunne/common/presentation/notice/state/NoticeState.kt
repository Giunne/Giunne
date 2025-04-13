package com.project.giunne.common.presentation.notice.state

import com.project.giunne.common.data.remote.response.NoticeResponse
import com.project.giunne.common.data.remote.response.PaginationInfo
import com.project.giunne.common.data.util.DataThrowable
import com.project.giunne.common.presentation.notice.content.Action

data class NoticeState(
    val isLoading: Boolean = false,
    val isOpen: Boolean = false,
    val isCreateNoticeDialog: Boolean = false,
    val isNoticeDetailDialog: Boolean = false,
    val isNoticeActionDialog: Boolean = false,
    val noticeCount: Int = 0,
    val error: DataThrowable? = null,
    val noticeList: List<NoticeResponse> = listOf(),
    val currentNotice: NoticeResponse = NoticeResponse(),
    val noticeActionState: NoticeActionState = NoticeActionState(),
    val paginationInfo: PaginationInfo = PaginationInfo()
) {
    data class NoticeActionState(
        val action: Action = Action.DELETE,
        val noticeId: Int = 0,
        val title: String = "",
        val content: String = ""
    )
}

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
