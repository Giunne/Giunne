package com.project.giunne.common.presentation.home.student.join

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.giunne.common.data.remote.request.AvatarLoginRequest
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.content.Loader
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.home.common.EmptyResult
import com.project.giunne.common.presentation.home.student.content.ResultRoadMapItem
import com.project.giunne.common.presentation.home.student.state.StudentJoinEvent
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import kotlinx.coroutines.launch

private const val TAG = "StudentJoinRecreationScreen"
@Composable
internal fun StudentJoinRecreationScreen(
    component: StudentJoinRecreationComponent,
    onBackClick: () -> Unit,
) {
    GLog.d(TAG, "onCreate")

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val joinState by component.uiState.collectAsStateWithLifecycle()
    var selectedItemIndex by remember { mutableIntStateOf(-1) }
    val isEnabled by remember {
        derivedStateOf {
            selectedItemIndex != -1
        }
    }

    LaunchedEffect(Unit) {
        component.getJoinRecreationList()
        component.sideEffect.collect { event ->
            when (event) {
                is StudentJoinEvent.SuccessLogin -> {
                    onBackClick()
                }

                is StudentJoinEvent.FailLogin -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
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
            if (joinState.isLoading) {
                Loader()
            }
            if (joinState.recreationStudentJoinList.isEmpty()) {
                EmptyResult(
                    modifier = Modifier.weight(1f),
                    description = "아직 참여중인 로드맵이 없습니다.",
                    highlightRegex = 8..10
                )
            }
            LazyColumn(
                modifier = Modifier
                    .weight(1f),
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(joinState.recreationStudentJoinList.size) { index ->
                    ResultRoadMapItem(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        isSelected = selectedItemIndex == index,
                        recreation = joinState.recreationStudentJoinList[index],
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
                    /* TODO(Token 갈아 끼워줘야함) */
                    if (isEnabled) {
                        component.loginRecreation(
                            AvatarLoginRequest(
                                playerId = joinState.recreationStudentJoinList[selectedItemIndex].id
                            )
                        )
                    }
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