package com.project.giunne.common.presentation.roadmap.student

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.toggle.GPToggleButton
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.gdp

private const val TAG = "StudentRoadmapScreen"
@Composable
internal fun StudentRoadmapScreen(
    component: StudentRoadmapComponent,
    modifier: Modifier = Modifier,
) {
    GLog.d(TAG, "onCreate")

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    var isSelected by remember { mutableStateOf(false) }

    val joggingWeek = listOf(
        13, 14, 15,
        12, 11, 10,
        7, 8, 9,
        6, 5, 4,
        1, 2, 3
    )

    Scaffold(
        modifier = Modifier
            .addFocusCleaner(focusManager)
            .fillMaxSize()
            .imePadding(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (isSelected) {
                JoggingRoadmapScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.gdp, vertical = 8.gdp)
                        .align(Alignment.Center),
                    joggingWeek = joggingWeek,
                )
            } else {
                ExerciseRoadmapScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
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
                    titleLeft = "운동",
                    titleRight = "조깅",
                    isSelected = isSelected,
                    onExerciseClick = {
                        isSelected = false
                    },
                    onJoggingClick = {
                        isSelected = true
                    }
                )
            }
        }
    }
}

