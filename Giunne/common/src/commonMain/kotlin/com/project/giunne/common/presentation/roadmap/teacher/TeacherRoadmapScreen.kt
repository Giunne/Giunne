package com.project.giunne.common.presentation.roadmap.teacher

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.gsp

private const val TAG = "TeacherRoadmapScreen"
@Composable
internal fun TeacherRoadmapScreen(
    component: TeacherRoadmapComponent,
    modifier: Modifier = Modifier,
) {
    GLog.d(TAG, "onCreate")

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

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
            GPText(
                modifier = Modifier.align(Alignment.Center),
                text = "TeacherRoadmapScreen",
                textSize = 20.gsp
            )
        }
    }
}
