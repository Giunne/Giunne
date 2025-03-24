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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.project.giunne.common.presentation.certification.student.intent.VideoUploadStore
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.common.presentation.certification.student.state.CertProgress
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.content.Loader
import com.project.giunne.common.presentation.common.dialog.GPAlertDialog
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
import java.io.File

private const val TAG = "StudentCertificationScreen"
@Composable
internal fun StudentCertificationScreen(
    component: StudentCertificationComponent,
    modifier: Modifier = Modifier,
    onCommunityButtonClicked: (CertPage) -> Unit
) {
    GLog.d(TAG, "onCreate")

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    val certificationState by component.uiState.collectAsState()
    val videoUploadStore = remember { VideoUploadStore(scope) }
    val videoUploadState by videoUploadStore.state.collectAsState()
    val checkProgressItem = certificationState.roadmapProgressList.find { it.questStateInfo.questProgress == CertProgress.CHECK.code }
    val uploadProgressItem = certificationState.roadmapProgressList.find { it.questStateInfo.questProgress == CertProgress.UPLOAD.code }

    LaunchedEffect(certificationState.pageType) {
        when (certificationState.pageType) {
            CertPage.RoadMap -> {
                component.callCertificationProgressList(1)
                component.callCertificationHistoryList(1)
            }
            CertPage.Running -> {
                component.callCertificationProgressList(2)
                component.callCertificationHistoryList(2)
            }
        }
    }

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
                onClick = { onCommunityButtonClicked(certificationState.pageType) },
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
                page = certificationState.pageType,
                onRoadmapClicked = {
                    scope.launch {
                        component.onClickRoadmapTap()
                    }
                },
                onRunningClicked = {
                    scope.launch {
                        component.onClickRunningTap()
                    }
                }
            )
            when(certificationState.pageType) {
                CertPage.RoadMap -> {
                    RoadMapCertScreen(
                        modifier = Modifier.fillMaxSize(),
                        videoUploadStore = videoUploadStore,
                        videoUploadState = videoUploadState,
                        onCertButtonClicked = {
                            component.onClickRoadmapCertButton()
                        },
                        checkProgressItem = checkProgressItem,
                        uploadProgressItem = uploadProgressItem,
                        roadmapHistoryList = certificationState.roadmapHistoryList,
                    )
                }
                CertPage.Running -> {
                    RunningCertScreen(
                        modifier = Modifier.fillMaxSize(),
                        onCertButtonClicked = {
                            component.onClickRunningCertButton()
                        },
                        runningProgressList = certificationState.runningProgressList,
                        runningHistoryList = certificationState.runningHistoryList,
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
                        checkProgressItem?.let { item ->
                            videoUploadState.videoFile?.let { file ->
                                component.uploadFile(
                                    item.id.toLong(),
                                    file.toByteArray(),
                                    file.getMimeType()
                                )
                            }
                        }
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
                    }
                }, /* TODO API */
                weekText = "2주차", /* TODO API */
            )
        }
    }

    with(certificationState.error) {
        if (this != null) {
            GPAlertDialog(
                dismiss = { component.dismissErrorDialog() },
                title = "인증 화면 에러",
                content = certificationState.error?.message.orEmpty(),
            )
        }
    }

    if (certificationState.loading) {
        Loader()
    }
}