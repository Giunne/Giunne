package com.project.giunne.common.presentation.certification.student

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FabPosition
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import com.project.giunne.Res
import com.project.giunne.common.presentation.certification.student.content.PageSelectRow
import com.project.giunne.common.presentation.certification.student.content.RoadMapCertScreen
import com.project.giunne.common.presentation.certification.student.content.RoadmapCertConfirmDialog
import com.project.giunne.common.presentation.certification.student.content.RunningCertConfirmDialog
import com.project.giunne.common.presentation.certification.student.content.RunningCertScreen
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.content.Loader
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_arrow_right
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

private const val TAG = "StudentCertificationScreen"
@Composable
internal fun StudentCertificationScreen(
    component: StudentCertificationComponent,
    modifier: Modifier = Modifier,
    onCommunityButtonClicked: () -> Unit
) {
    GLog.d(TAG, "onCreate")

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    ///// test /////
    var page by remember { mutableStateOf(CertPage.RoadMap) }

    var loading by remember { mutableStateOf(false) }
    var roadmapStep by remember { mutableStateOf<Int?>(null) }
    var runningStep by remember { mutableStateOf<Int?>(null) }
    ////////////////

    val certificationState by component.uiState.collectAsState()

    Scaffold(
        modifier = Modifier
            .addFocusCleaner(focusManager)
            .fillMaxSize()
            .imePadding(),
        floatingActionButton = {
            GPButton(
                modifier = Modifier
                    .width(88.gdp)
                    .height(40.gdp),
                normalColor = GPColor.ButtonOrange,
                pressColor = GPColor.ButtonPressOrange,
                hoverColor = GPColor.ButtonHoverOrange,
                onClick = { onCommunityButtonClicked() },
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GPText(
                        text = "게시판", /* TODO String */
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.White
                    )
                    SpW(8.gdp)
                    Image(
                        modifier = Modifier.height(14.gdp),
                        painter = painterResource(Res.drawable.icon_arrow_right),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(GPColor.White)
                    )
                }
            }
        },
    ) {
        Column(
            modifier = Modifier
                .background(GPColor.BackgroundLightGray)
                .fillMaxSize()
                .padding(horizontal = 16.gdp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PageSelectRow(
                modifier = Modifier
                    .padding(vertical = 10.gdp)
                    .background(
                        color = GPColor.White,
                        shape = RoundedCornerShape(16.gdp)
                    )
                    .fillMaxWidth()
                    .height(64.gdp)
                    .padding(horizontal = 8.gdp),
                page = page,
                onRoadmapClicked = {
                    scope.launch { /* TODO API */
                        loading = true
                        delay(1000)
                        loading = false
                        page = CertPage.RoadMap
                    }
                },
                onRunningClicked = {
                    scope.launch { /* TODO API */
                        loading = true
                        delay(1000)
                        loading = false
                        page = CertPage.Running
                    }
                }
            )
            when(page) {
                CertPage.RoadMap -> {
                    RoadMapCertScreen(
                        modifier = Modifier.fillMaxSize(),
                        onCertButtonClicked = {
                            component.onClickRoadmapCertButton()
                        },
                        step = roadmapStep
                    )
                }
                CertPage.Running -> {
                    RunningCertScreen(
                        modifier = Modifier.fillMaxSize(),
                        onCertButtonClicked = {
                            component.onClickRunningCertButton()
                        },
                        step = runningStep
                    )
                }
            }
        }
    }

    with(certificationState.roadmapCertConfirmDialog) {
        if (this) {
            RoadmapCertConfirmDialog(
                onDismissButtonClicked = { component.dismissRoadmapCertDialog() },
                onConfirmButtonClicked = {
                    component.dismissRoadmapCertDialog()
                    scope.launch {
                        loading = true
                        delay(1000)
                        loading = false
                        roadmapStep = 1
                    }
                }, /* TODO API */
                levelText = "3단계 비스트", /* TODO API */
            )
        }
    }

    with(certificationState.runningCertConfirmDialog) {
        if (this) {
            RunningCertConfirmDialog(
                onDismissButtonClicked = { component.dismissRunningCertDialog() },
                onConfirmButtonClicked = {
                    component.dismissRunningCertDialog()
                    scope.launch {
                        loading = true
                        delay(1000)
                        loading = false
                        runningStep = 1
                    }
                }, /* TODO API */
                weekText = "2주차", /* TODO API */
            )
        }
    }

    if (loading) {
        Loader()
    }
}