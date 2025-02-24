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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.content.Loader
import com.project.giunne.common.presentation.common.scrollbar.VerticalScrollbar
import com.project.giunne.common.presentation.community.student.content.CommunityItemRow
import com.project.giunne.common.presentation.community.student.content.SearchRow
import com.project.giunne.common.presentation.community.student.content.SelectableDialog
import com.project.giunne.common.presentation.community.student.dummy.CommunityDto
import com.project.giunne.common.presentation.community.student.dummy.roadmapCommunityList
import com.project.giunne.common.presentation.community.student.dummy.runningCommunityList
import com.project.giunne.common.presentation.community.student.state.DatePriority
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.gdp
import org.jetbrains.compose.resources.painterResource

private const val TAG = "StudentCommunityScreen"
@Composable
internal fun StudentCommunityScreen(
    component: StudentCommunityComponent,
    modifier: Modifier = Modifier,
    navigateToDetail: (CommunityDto) -> Unit,
    pageType: CertPage
) {
    GLog.d(TAG, "onCreate")

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    val scrollState = rememberLazyListState()
    val communityState by component.uiState.collectAsState()

    ///// test /////
    var list by remember { mutableStateOf(
        when(pageType) {
            CertPage.RoadMap -> roadmapCommunityList
            else -> runningCommunityList
        }
    ) }
    ////////////////

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
                datePriority = communityState.datePriority,
                roadmapFilter = communityState.roadmapFilter,
                runningFilter = communityState.runningFilter,
                onSearchButtonClicked = {
                    component.onClickSearchButton(it)
                },
                onDatePriorityButtonClicked = { component.onClickDatePriorityButton() },
                onRoadmapFilterButtonClicked = { component.onClickRoadmapFilterButton() },
                onRunningFilterButtonClicked = { component.onClickRunningFilterButton() },
            )
//            EmptyBox()
            Box {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.gdp),
                    state = scrollState
                ) {
                    items(
                        count = list.size
                    ) {
                        CommunityItemRow(
                            name = list[it].name,
                            painter = painterResource(list[it].character),
                            date = list[it].date,
                            commentCount = list[it].commentCount,
                            content = list[it].content,
                            type = pageType,
                            onClick = { navigateToDetail(list[it]) },
                        )
                    }
                }
                VerticalScrollbar(
                    modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                    state = scrollState
                )
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
                },
                filterList = listOf( /* TODO API */
                    "1단계 버드독", "2단계 데드버그", "3단계 비스트", "4-a단계 하이플랭크", "4-b단계 플랭크", "5-c단계 플랭크 앤 플랭크",
                    "1단계 버드독", "2단계 데드버그", "3단계 비스트", "4-a단계 하이플랭크", "4-b단계 플랭크", "5-c단계 플랭크 앤 플랭크",
                    "1단계 버드독", "2단계 데드버그", "3단계 비스트", "4-a단계 하이플랭크", "4-b단계 플랭크", "5-c단계 플랭크 앤 플랭크",
                    "1단계 버드독", "2단계 데드버그", "3단계 비스트", "4-a단계 하이플랭크", "4-b단계 플랭크", "5-c단계 플랭크 앤 플랭크",
                    "1단계 버드독", "2단계 데드버그", "3단계 비스트", "4-a단계 하이플랭크", "4-b단계 플랭크", "5-c단계 플랭크 앤 플랭크",
                    "1단계 버드독", "2단계 데드버그", "3단계 비스트", "4-a단계 하이플랭크", "4-b단계 플랭크", "5-c단계 플랭크 앤 플랭크",
                    "1단계 버드독", "2단계 데드버그", "3단계 비스트", "4-a단계 하이플랭크", "4-b단계 플랭크", "5-c단계 플랭크 앤 플랭크",
                    "1단계 버드독", "2단계 데드버그", "3단계 비스트", "4-a단계 하이플랭크", "4-b단계 플랭크", "5-c단계 플랭크 앤 플랭크",
                    "1단계 버드독", "2단계 데드버그", "3단계 비스트", "4-a단계 하이플랭크", "4-b단계 플랭크", "5-c단계 플랭크 앤 플랭크",
                    "1단계 버드독", "2단계 데드버그", "3단계 비스트", "4-a단계 하이플랭크", "4-b단계 플랭크", "5-c단계 플랭크 앤 플랭크",
                ),
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
                },
                filterList = listOf( /* TODO API */
                    "1주차", "2주차", "3주차", "4주차", "5주차"
                ),
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
                },
                filterList = listOf("최신순", "오래된순"),
            )
        }
    }

    if (communityState.loading) {
        Loader()
    }
}