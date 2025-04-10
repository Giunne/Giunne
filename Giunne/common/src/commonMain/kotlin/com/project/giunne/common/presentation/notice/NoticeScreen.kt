package com.project.giunne.common.presentation.notice

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
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
import com.project.giunne.common.presentation.common.topbar.GPMainTopBar
import com.project.giunne.common.presentation.notice.content.EmptyList
import com.project.giunne.common.presentation.notice.content.NoticeItem
import com.project.giunne.common.presentation.notice.intent.NoticeStore
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
    }

    LaunchedEffect(endOfListReached) {
        if (endOfListReached && noticeState.paginationInfo.currentPage != noticeState.paginationInfo.totalPage) {
            noticeStore.loadNextPage(noticeState.paginationInfo.currentPage + 1, Define.recreationId)
        }
    }

    Scaffold(
        modifier = modifier
            .addFocusCleaner(focusManager),
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
                                .size(32.gdp),
                            icon = {
                                Image(
                                    modifier = Modifier.size(16.gdp),
                                    painter = painterResource(Res.drawable.icon_write),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(GPColor.White)
                                )
                            },
                            normalColor = GPColor.ButtonBlack,
                            pressColor = GPColor.ButtonPressBlack,
                            hoverColor = GPColor.ButtonHoverBlack,
                            onClick = {
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
        } else {
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

                            }
                        )
                    }
                }
            }
        }
    }
}