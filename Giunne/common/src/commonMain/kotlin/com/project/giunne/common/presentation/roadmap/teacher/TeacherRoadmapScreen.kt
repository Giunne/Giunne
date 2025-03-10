package com.project.giunne.common.presentation.roadmap.teacher

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.content.Loader
import com.project.giunne.common.presentation.common.toggle.GPToggleButton
import com.project.giunne.common.presentation.roadmap.teacher.state.RoadMapEvent
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.gdp
import kotlinx.coroutines.async

private const val TAG = "TeacherRoadmapScreen"

@Composable
internal fun TeacherRoadmapScreen(
    component: TeacherRoadmapComponent,
    modifier: Modifier = Modifier,
) {
    GLog.d(TAG, "onCreate")

    val focusManager = LocalFocusManager.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val roadMapState by component.uiState.collectAsStateWithLifecycle()
    var isSelected by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        async {
            component.getAllRoadMap()
            component.getTeacherCourse(1)
        }.await()
    }

    Scaffold(
        modifier = Modifier
            .addFocusCleaner(focusManager)
            .fillMaxSize()
            .imePadding(),
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (roadMapState.isLoading) {
                Loader()
            }
            if (isSelected) {
                TeacherJoggingRoadmapScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(GPColor.BackgroundLightGray)
                        .padding(horizontal = 16.gdp, vertical = 8.gdp)
                        .align(Alignment.Center),
                )
            } else {
                TeacherExerciseRoadmapScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    roadMapComponent = component,
                    roadMapState = roadMapState
                )
            }
            Box(
                modifier = Modifier
                    .padding(16.gdp)
                    .align(Alignment.BottomEnd)
            ) {
                GPToggleButton(
                    modifier = Modifier
                        .width(120.gdp)
                        .height(44.gdp),
                    titleLeft = roadMapState.roadMapInfo.getOrNull(0)?.title.orEmpty(),
                    titleRight = roadMapState.roadMapInfo.getOrNull(1)?.title.orEmpty(),
                    isSelected = isSelected,
                    onLeftButtonClick = {
                        isSelected = false
                        component.getTeacherCourse(1)
                    },
                    onRightButtonClick = {
                        isSelected = true
                        component.getTeacherCourse(2)
                    }
                )
            }
        }
    }
}