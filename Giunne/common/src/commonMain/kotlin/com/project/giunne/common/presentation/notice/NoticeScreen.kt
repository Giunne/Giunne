package com.project.giunne.common.presentation.notice

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.button.GPBackButton
import com.project.giunne.common.presentation.common.button.GPIconButton
import com.project.giunne.common.presentation.common.content.Loader
import com.project.giunne.common.presentation.common.dialog.GPAlertDialog
import com.project.giunne.common.presentation.common.topbar.GPMainTopBar
import com.project.giunne.common.presentation.notice.content.Action
import com.project.giunne.common.presentation.notice.content.CreateNoticeDialog
import com.project.giunne.common.presentation.notice.content.EmptyList
import com.project.giunne.common.presentation.notice.content.NoticeItem
import com.project.giunne.common.presentation.notice.content.TeacherNoticeDetailDialog
import com.project.giunne.common.presentation.notice.intent.NoticeStore
import com.project.giunne.common.presentation.notice.state.NoticeEvent
import com.project.giunne.common.presentation.notice.state.NoticeState
import com.project.giunne.common.presentation.signup.SignupComponent.Companion.TYPE_TEACHER
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.Define
import com.project.giunne.common.util.gdp
import com.project.giunne.icon_write
import org.jetbrains.compose.resources.painterResource

@Composable
fun NoticeScreen(
    modifier: Modifier = Modifier,
    noticeStore: NoticeStore,
    noticeState: NoticeState,
    onBackButtonClicked: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scrollState = rememberLazyListState()

    val endOfListReached by remember {
        derivedStateOf {
            val lastVisibleItem = scrollState.layoutInfo.visibleItemsInfo.lastOrNull()
            val totalItemsCount = scrollState.layoutInfo.totalItemsCount
            noticeState.paginationInfo.hasNextPage && lastVisibleItem != null && lastVisibleItem.index >= totalItemsCount - 1
        }
    }

    LaunchedEffect(Unit) {
        noticeStore.getNoticeList(1, Define.recreationId)

        noticeStore.sideEffect.collect { event ->
            noticeStore.getNoticeList(1, Define.recreationId)
            when (event) {
                is NoticeEvent.CreateNewNotice -> {
                    snackbarHostState.showSnackbar(event.message)
                }
                is NoticeEvent.DeleteNotice -> {
                    noticeStore.onDismissNoticeDetailDialog()
                    snackbarHostState.showSnackbar(event.message)
                }
                is NoticeEvent.ModifyNotice -> {
                    noticeStore.onDismissNoticeDetailDialog()
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    LaunchedEffect(endOfListReached) {
        if (endOfListReached && noticeState.paginationInfo.currentPage != noticeState.paginationInfo.totalPage) {
            noticeStore.loadNextPage(
                noticeState.paginationInfo.currentPage + 1,
                Define.recreationId
            )
        }
    }

    Scaffold(
        modifier = modifier
            .addFocusCleaner(focusManager),
        snackbarHost = {
            SnackbarHost(
                snackbarHostState
            )
        },
        topBar = {
            GPMainTopBar(
                titleText = "공지사항",
                leftIcon = {
                    GPBackButton { onBackButtonClicked() }
                },
                rightIcon = {
                    if (Define.userRole == TYPE_TEACHER) {
                        GPIconButton(
                            modifier = Modifier
                                .padding(end = 16.gdp)
                                .size(28.gdp),
                            icon = {
                                Image(
                                    modifier = Modifier.size(14.gdp),
                                    painter = painterResource(Res.drawable.icon_write),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(GPColor.White)
                                )
                            },
                            normalColor = GPColor.ButtonBlack,
                            pressColor = GPColor.ButtonPressBlack,
                            hoverColor = GPColor.ButtonHoverBlack,
                            onClick = {
                                noticeStore.onClickCreateNoticeDialog()
                            },
                            shadow = false
                        )
                    }
                }
            )
        }
    ) {
        if (noticeState.isLoading) {
            Loader()
        }

        if (noticeState.noticeList.isEmpty()) {
            EmptyList()
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = GPColor.BackgroundLightGray)
                    .padding(it),
            ) {
                items(
                    items = noticeState.noticeList,
                    key = { notice ->
                        notice.id
                    }
                ) { noticeData ->
                    NoticeItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(66.gdp),
                        noticeData = noticeData,
                        onClick = { id ->
                            noticeStore.getNoticeDetail(id)
                        }
                    )
                }
            }
        }

        if (noticeState.isCreateNoticeDialog) {
            CreateNoticeDialog(
                modifier = Modifier
                    .padding(16.gdp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                onDismiss = {
                    noticeStore.onDismissCreateNoticeDialog()
                },
                onConfirm = { title, content ->
                    noticeStore.createNewNotice(Define.recreationId, title, content)
                }
            )
        }

        if (noticeState.isNoticeDetailDialog) {
            TeacherNoticeDetailDialog(
                noticeData = noticeState.currentNotice,
                onDismiss = {
                    noticeStore.onDismissNoticeDetailDialog()
                },
                onConfirm = { action, noticeId, title, content ->
                    when (action) {
                        Action.MODIFY -> {
                            noticeStore.modifyNotice(
                                noticeId,
                                title,
                                content,
                            )
                        }
                        Action.DELETE -> {
                            noticeStore.deleteNotice(noticeId)
                        }
                    }
                }
            )
        }

        if (noticeState.error != null) {
            GPAlertDialog(
                title = "공지사항 에러",
                content = noticeState.error.message.toString(),
                dismiss = {
                    noticeStore.dismissErrorDialog()
                }
            )
        }

    }
}