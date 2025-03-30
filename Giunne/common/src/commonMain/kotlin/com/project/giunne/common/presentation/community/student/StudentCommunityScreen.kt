package com.project.giunne.common.presentation.community.student

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.project.giunne.common.data.remote.response.QuestTypeInfo
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.content.Loader
import com.project.giunne.common.presentation.common.scrollbar.VerticalScrollbar
import com.project.giunne.common.presentation.community.content.CommunityItemRow
import com.project.giunne.common.presentation.community.content.EmptyBox
import com.project.giunne.common.presentation.community.content.SearchRow
import com.project.giunne.common.presentation.community.content.SelectableDialog
import com.project.giunne.common.presentation.community.student.dummy.CommunityDto
import com.project.giunne.common.presentation.community.student.dummy.roadmapCommunityList
import com.project.giunne.common.presentation.community.student.dummy.runningCommunityList
import com.project.giunne.common.presentation.community.student.state.DatePriority
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.gdp
import kotlinx.serialization.json.JsonNull.content
import org.jetbrains.compose.resources.painterResource

private const val TAG = "StudentCommunityScreen"
@Composable
internal fun StudentCommunityScreen(
    component: StudentCommunityComponent,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long, String) -> Unit,
    pageType: CertPage
) {
    GLog.d(TAG, "onCreate")

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    val scrollState = rememberLazyListState()
    val communityState by component.uiState.collectAsState()

    LaunchedEffect(Unit) {
        when (pageType) {
            CertPage.RoadMap -> {
                component.callPostingList(
                    roadMapId = 1,
                    questName = communityState.roadmapFilter,
                    nickName = communityState.searchText,
                    pageIndex = 1,
                    sortDirection = communityState.datePriority.code,
                )
                component.callQuestTypeList(
                    roadmapId = 1
                )
            }
            CertPage.Running -> {
                component.callPostingList(
                    roadMapId = 2,
                    questName = communityState.runningFilter,
                    nickName = communityState.searchText,
                    pageIndex = 1,
                    sortDirection = communityState.datePriority.code,
                )
                component.callQuestTypeList(
                    roadmapId = 2
                )
            }
        }
    }

    val endOfListReached by remember {
        derivedStateOf {
            val lastVisibleItem = scrollState.layoutInfo.visibleItemsInfo.lastOrNull()
            val totalItemsCount = scrollState.layoutInfo.totalItemsCount

            communityState.paginationInfo.hasNextPage && lastVisibleItem != null && lastVisibleItem.index >= totalItemsCount - 1
        }
    }

    LaunchedEffect(endOfListReached) {
        if (endOfListReached && communityState.paginationInfo.currentPage != communityState.paginationInfo.totalPage) {
            component.callPostingList(
                roadMapId = when (pageType) {
                    CertPage.RoadMap -> 1
                    CertPage.Running -> 2
                },
                questName = when (pageType) {
                    CertPage.RoadMap -> communityState.roadmapFilter
                    CertPage.Running -> communityState.runningFilter
                },
                nickName = communityState.searchText,
                pageIndex = communityState.paginationInfo.currentPage + 1,
                sortDirection = communityState.datePriority.code,
            )
        }
    }

    Scaffold(
        modifier = Modifier
            .addFocusCleaner(focusManager)
            .fillMaxSize()
            .imePadding(),
    ) {
        Column(
            modifier = Modifier
                .background(GPColor.BackgroundLightGray)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.gdp)
                    .height(52.gdp),
                pageType = pageType,
                searchText = communityState.searchText,
                onSearchTextChanged = { component.onSearchTextChanged(it) },
                datePriority = communityState.datePriority,
                roadmapFilter = communityState.roadmapFilter.ifEmpty { "전체" },
                runningFilter = communityState.runningFilter.ifEmpty { "전체" },
                onSearchButtonClicked = {
                    component.onClickSearchButton(
                        roadMapId = when (pageType) {
                            CertPage.RoadMap -> 1
                            CertPage.Running -> 2
                        },
                        questName = when (pageType) {
                            CertPage.RoadMap -> communityState.roadmapFilter
                            CertPage.Running -> communityState.runningFilter
                        },
                        nickName = communityState.searchText,
                        sortDirection = communityState.datePriority.code,
                    )
                },
                onDatePriorityButtonClicked = { component.onClickDatePriorityButton() },
                onRoadmapFilterButtonClicked = { component.onClickRoadmapFilterButton() },
                onRunningFilterButtonClicked = { component.onClickRunningFilterButton() },
            )
            Box {
                if (communityState.postingList.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.gdp),
                        state = scrollState
                    ) {
                        items(
                            count = communityState.postingList.size
                        ) {
                            CommunityItemRow(
                                postingInfo = communityState.postingList[it],
                                onClick = {
                                    navigateToDetail(
                                        communityState.postingList[it].id,
                                        communityState.postingList[it].getQuestTitle()
                                    )
                                },
                            )
                        }
                    }
                    VerticalScrollbar(
                        modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                        state = scrollState
                    )
                } else {
                    EmptyBox()
                }
            }
        }
    }

    with(communityState.roadmapFilterDialog) {
        if (this) {
            SelectableDialog(
                dismiss = { component.dismissRoadmapFilterDialog() },
                onSelect = { text ->
                    component.onSelectRoadmapFilter(text)
                    component.dismissRoadmapFilterDialog()
                    component.onClickSearchButton(
                        roadMapId = when (pageType) {
                            CertPage.RoadMap -> 1
                            CertPage.Running -> 2
                        },
                        questName = text,
                        nickName = communityState.searchText,
                        sortDirection = communityState.datePriority.code,
                    )
                },
                filterList = communityState.questTypeList
            )
        }
    }

    with(communityState.runningFilterDialog) {
        if (this) {
            SelectableDialog(
                dismiss = { component.dismissRunningFilterDialog() },
                onSelect = { text ->
                    component.onSelectRunningFilter(text)
                    component.dismissRunningFilterDialog()
                    component.onClickSearchButton(
                        roadMapId = when (pageType) {
                            CertPage.RoadMap -> 1
                            CertPage.Running -> 2
                        },
                        questName = text,
                        nickName = communityState.searchText,
                        sortDirection = communityState.datePriority.code,
                    )
                },
                filterList = communityState.questTypeList
            )
        }
    }

    with(communityState.datePrioritySelectDialog) {
        if (this) {
            SelectableDialog(
                dismiss = { component.dismissDatePriorityDialog() },
                onSelect = { text ->
                    component.onSelectDatePriority(
                        if (text == "최신순") DatePriority.NEWEST
                        else DatePriority.OLDEST
                    )
                    component.dismissDatePriorityDialog()
                    component.onClickSearchButton(
                        roadMapId = when (pageType) {
                            CertPage.RoadMap -> 1
                            CertPage.Running -> 2
                        },
                        questName = when (pageType) {
                            CertPage.RoadMap -> communityState.roadmapFilter
                            CertPage.Running -> communityState.runningFilter
                        },
                        nickName = communityState.searchText,
                        sortDirection = if (text == "최신순") DatePriority.NEWEST.code
                            else DatePriority.OLDEST.code
                    )
                },
                filterList = listOf(
                    QuestTypeInfo(questName = "최신순"),
                    QuestTypeInfo(questName = "오래된순")
                ),
            )
        }
    }

    if (communityState.loading) {
        Loader()
    }
}