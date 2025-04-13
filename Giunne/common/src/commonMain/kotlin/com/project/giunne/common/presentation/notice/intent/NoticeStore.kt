package com.project.giunne.common.presentation.notice.intent

import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.data.remote.request.CreateNoticeRequest
import com.project.giunne.common.data.remote.request.ModifyNoticeRequest
import com.project.giunne.common.data.util.asDataThrowable
import com.project.giunne.common.domain.usecase.notice.DeleteNoticeUseCase
import com.project.giunne.common.domain.usecase.notice.GetNoticeDetailUseCase
import com.project.giunne.common.domain.usecase.notice.GetNoticeListUseCase
import com.project.giunne.common.domain.usecase.notice.ModifyNoticeUseCase
import com.project.giunne.common.domain.usecase.notice.PostNoticeUseCase
import com.project.giunne.common.domain.usecase.notice.ReadNoticeUseCase
import com.project.giunne.common.domain.usecase.notice.UnreadNoticeCountUseCase
import com.project.giunne.common.presentation.notice.content.Action
import com.project.giunne.common.presentation.notice.state.NoticeEvent
import com.project.giunne.common.presentation.notice.state.NoticeState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

class NoticeStore(
    private val getNoticeListUseCase: GetNoticeListUseCase = KoinJavaComponent.get(GetNoticeListUseCase::class.java),
    private val getNoticeDetailUseCase: GetNoticeDetailUseCase = KoinJavaComponent.get(GetNoticeDetailUseCase::class.java),
    private val postNoticeUseCase: PostNoticeUseCase = KoinJavaComponent.get(PostNoticeUseCase::class.java),
    private val modifyNoticeUseCase: ModifyNoticeUseCase = KoinJavaComponent.get(ModifyNoticeUseCase::class.java),
    private val deleteNoticeUseCase: DeleteNoticeUseCase = KoinJavaComponent.get(DeleteNoticeUseCase::class.java),
    private val readNoticeUseCase: ReadNoticeUseCase = KoinJavaComponent.get(ReadNoticeUseCase::class.java),
    private val unreadNoticeCountUseCase: UnreadNoticeCountUseCase = KoinJavaComponent.get(UnreadNoticeCountUseCase::class.java)
): BaseComponent<NoticeState, NoticeEvent>(
    scope = CoroutineScope(Dispatchers.IO),
    initialState = NoticeState()
) {

    fun getNoticeList(
        pageIndex: Int,
        recreationId: Int
    ) {
        scope.launch {
            setState { copy(isLoading = true) }
            runCatching {
                getNoticeListUseCase(pageIndex, recreationId)
            }.onSuccess { response ->
                setState {
                    copy(
                        isLoading = false,
                        noticeList = response.list,
                        paginationInfo = response.paginationInfo
                    )
                }
            }.onFailure {
                setState {
                    copy(
                        isLoading = false,
                        error = it.asDataThrowable()
                    )
                }
            }
        }
    }

    fun loadNextPage(
        pageIndex: Int,
        recreationId: Int
    ) {
        scope.launch {
            setState { copy(isLoading = true) }
            runCatching {
                getNoticeListUseCase(pageIndex, recreationId)
            }.onSuccess { response ->
                setState {
                    copy(
                        isLoading = false,
                        noticeList = (noticeList + response.list).distinctBy { it.id },
                        paginationInfo = response.paginationInfo
                    )
                }
            }.onFailure {
                setState {
                    copy(
                        isLoading = false,
                        error = it.asDataThrowable()
                    )
                }
            }
        }
    }

    fun createNewNotice(
        recreationId: Int,
        title: String,
        content: String
    ) {
        scope.launch {
            setState { copy(isLoading = true) }
            runCatching {
                postNoticeUseCase(
                    CreateNoticeRequest(
                        recreationId = recreationId,
                        title = title,
                        content = content
                    )
                )
            }.onSuccess {
                setState {
                    copy(
                        isLoading = false,
                        isCreateNoticeDialog = false
                    )
                }
                postSideEffect(NoticeEvent.CreateNewNotice("공지사항이 등록되었습니다!"))
            }.onFailure {
                setState {
                    copy(
                        isLoading = false,
                        isCreateNoticeDialog = false,
                        error = it.asDataThrowable()
                    )
                }
            }
        }

    }
    fun getNoticeDetail(
        noticeId: Int
    ) {
        scope.launch {
            runCatching {
                setState { copy(isLoading = true) }
                getNoticeDetailUseCase(noticeId)
            }.onSuccess { response ->
                setState {
                    copy(
                        isLoading = false,
                        isNoticeDetailDialog = true,
                        currentNotice = response
                    )
                }
            }.onFailure {
                setState {
                    copy(
                        isLoading = false,
                        error = it.asDataThrowable()
                    )
                }
            }
        }
    }

    fun modifyNotice(
        noticeId: Int,
        title: String,
        content: String
    ) {
        scope.launch {
            runCatching {
                setState { copy(isLoading = true) }
                modifyNoticeUseCase(
                    ModifyNoticeRequest(
                        id = noticeId,
                        title = title,
                        content = content
                    )
                )
            }.onSuccess {
                setState {
                    copy(
                        isLoading = false,
                        isNoticeActionDialog = false
                    )
                }
                postSideEffect(NoticeEvent.ModifyNotice(noticeId,"공지사항이 수정되었습니다."))
            }.onFailure {
                setState {
                    copy(
                        isLoading = false,
                        error = it.asDataThrowable()
                    )
                }
            }
        }
    }

    fun deleteNotice(
        noticeId: Int
    ) {
        scope.launch {
            runCatching {
                setState { copy(isLoading = true) }
                deleteNoticeUseCase(noticeId)
            }.onSuccess {
                setState {
                    copy(
                        isLoading = false,
                        isNoticeActionDialog = false
                    )
                }
                postSideEffect(NoticeEvent.DeleteNotice("공지사항이 삭제되었습니다."))
            }.onFailure {
                setState {
                    copy(
                        isLoading = false,
                        error = it.asDataThrowable()
                    )
                }
            }
        }
    }

    fun readNotice(
        noticeId: Int
    ) {
        scope.launch {
            runCatching {
                setState { copy(isLoading = true) }
                readNoticeUseCase(noticeId)
            }.onSuccess {
                setState { copy(isLoading = false) }
                postSideEffect(NoticeEvent.ReadNotice)
            }.onFailure {
                setState { copy(isLoading = false) }
            }
        }
    }


    fun getNoticeCount() {
        scope.launch {
            runCatching {
                unreadNoticeCountUseCase()
            }.onSuccess { response ->
                setState { copy(noticeCount = response.count) }
            }
        }
    }

    fun setNoticeActionData(action: Action, noticeId: Int, title: String, content: String) {
        setState {
            copy(
                isNoticeActionDialog = true,
                noticeActionState = NoticeState.NoticeActionState(
                    action = action,
                    noticeId = noticeId,
                    title = title,
                    content = content
                )
            )
        }
    }

    fun onDismissNoticeActionDialog() {
        setState { copy(isNoticeActionDialog = false) }
    }

    fun onDismissNoticeDetailDialog() {
        setState { copy(isNoticeDetailDialog = false) }
    }

    fun onClickCreateNoticeDialog() {
        setState { copy(isCreateNoticeDialog = true) }
    }

    fun onDismissCreateNoticeDialog() {
        setState { copy(isCreateNoticeDialog = false) }
    }


    fun onClickNotificationButton() {
        setState { copy(isOpen = true) }
    }

    fun closeNotificationScreen() {
        setState { copy(isOpen = false) }
    }

    fun dismissErrorDialog() {
        setState { copy(error = null) }
    }
}