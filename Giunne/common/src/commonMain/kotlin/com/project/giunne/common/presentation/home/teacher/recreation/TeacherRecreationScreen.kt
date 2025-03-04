package com.project.giunne.common.presentation.home.teacher.recreation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.content.Loader
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.home.common.EmptyResult
import com.project.giunne.common.presentation.home.student.content.ResultRoadMapItem
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

private const val TAG = "TeacherRecreationScreen"
@Composable
internal fun TeacherRecreationScreen(
    component: TeacherRecreationComponent,
    onBackClick: () -> Unit,
) {

    GLog.d(TAG, "onCreate")
    val focusManager = LocalFocusManager.current
    val lazyListState = rememberLazyListState()
    val snackbarHostState = remember { SnackbarHostState() }
    val teacherRecreationState by component.uiState.collectAsStateWithLifecycle()
    var selectedItemIndex by remember { mutableIntStateOf(-1) }
    val isEnabled by remember {
        derivedStateOf {
            selectedItemIndex != -1
        }
    }

    LaunchedEffect(Unit) {
        component.getTeacherRecreationList(1)
    }

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.layoutInfo }
            .collect { layoutInfo ->
                val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                val totalItemsCount = layoutInfo.totalItemsCount

                if (teacherRecreationState.paginationInfo.hasNextPage && lastVisibleItemIndex >= totalItemsCount - 1) {
                    component.loadMore(teacherRecreationState.paginationInfo.currentPage + 1)
                }
            }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(GPColor.BackgroundLightGray)
            .imePadding(),
        snackbarHost = {
            SnackbarHost(
                snackbarHostState
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GPColor.BackgroundLightGray)
                .addFocusCleaner(focusManager)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (teacherRecreationState.isLoading) {
                Loader()
            }
            if (teacherRecreationState.recreationList.isEmpty()) {
                EmptyResult(
                    modifier = Modifier.weight(1f),
                    description = "아직 생성한 로드맵이 없습니다.",
                    highlightRegex = 7..9
                )
            }
            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(teacherRecreationState.recreationList.size) { index ->
                    ResultRoadMapItem(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        isSelected = selectedItemIndex == index,
                        recreationName = teacherRecreationState.recreationList[index].recreationName,
                        teacherName = teacherRecreationState.recreationList[index].teacherName,
                        onItemSelected = {
                            selectedItemIndex = if (selectedItemIndex == index) {
                                -1
                            } else {
                                index
                            }
                            focusManager.clearFocus()
                        }
                    )
                }
            }

            GPButton(
                modifier = Modifier
                    .padding(16.gdp)
                    .fillMaxWidth()
                    .height(56.gdp),
                normalColor = if (isEnabled) GPColor.ButtonOrange else GPColor.ButtonLightGray,
                pressColor = if (isEnabled) GPColor.ButtonPressOrange else GPColor.ButtonLightGray,
                hoverColor = if (isEnabled) GPColor.ButtonHoverOrange else GPColor.ButtonLightGray,
                onClick = {

                },
            ) {
                GPText(
                    text = "선택하기",
                    textSize = 14.gsp,
                    fontFamily = GPFontFamily.Bold,
                    textColor = GPColor.White
                )
            }
        }
    }
}