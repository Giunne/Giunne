package com.project.giunne.common.presentation.home.student.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.giunne.common.presentation.certification.student.state.CertProgress
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.content.Loader
import com.project.giunne.common.presentation.common.dialog.GPLevelUpDialog
import com.project.giunne.common.presentation.common.dialog.GPSuccessRoadMapDialog
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.home.common.EmptyResult
import com.project.giunne.common.presentation.home.student.content.ResultRoadMapItem
import com.project.giunne.common.presentation.home.student.content.StudentCharacter
import com.project.giunne.common.presentation.home.student.content.StudentRoadMapLevelBox
import com.project.giunne.common.presentation.home.student.content.TeacherCheckingBox
import com.project.giunne.common.presentation.home.student.state.StudentHomeEvent
import com.project.giunne.common.presentation.roadmap.node.NodeStatus
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.AvatarUtil
import com.project.giunne.common.util.Define
import com.project.giunne.common.util.Define.currentExerciseId
import com.project.giunne.common.util.Define.currentJoggingId
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

private const val TAG = "StudentHomeScreen"
@Composable
internal fun StudentHomeScreen(
    component: StudentHomeComponent,
    modifier: Modifier = Modifier,
    navigateToCommunity: () -> Unit,
    navigateToSearchRoadMap: () -> Unit,
    navigateToJoinRoadMap: () -> Unit,
) {
    GLog.d(TAG, "onCreate")

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val homeState by component.uiState.collectAsStateWithLifecycle()
    val userInfoState = AvatarUtil.uiState.collectAsState()
    val levelUpEffects by AvatarUtil.levelUpEffects.collectAsState(false)

    LaunchedEffect(Define.playerId) {
        if (Define.playerId != 0L) {
            async {
                component.checkStudentRoadMapState(currentExerciseId, 1, Define.playerId.toInt())
                component.checkStudentRoadMapState(currentJoggingId, 2, Define.playerId.toInt())
            }.await()
        }
    }

    LaunchedEffect(Unit) {
        component.sideEffect.collect { event ->
            when (event) {
                is StudentHomeEvent.ErrorSnackBar -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = event.message
                        )
                    }
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .addFocusCleaner(focusManager)
            .fillMaxSize()
            .background(GPColor.BackgroundLightGray)
            .imePadding(),
        snackbarHost = {
            SnackbarHost(
                snackbarHostState
            )
        }
    ) {
        if (Define.playerId != 0L && homeState.isLoading) {
            Loader()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(GPColor.BackgroundLightGray),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (Define.playerId == 0L) {
                    EmptyResult(
                        modifier = Modifier
                            .weight(1f)
                            .background(GPColor.BackgroundLightGray),
                        description = "아직 진행중인 로드맵이 없습니다.",
                        highlightRegex = 8..10
                    )
                } else {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .background(GPColor.BackgroundLightGray)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(16.gdp)
                    ) {
                        StudentCharacter(
                            level = homeState.avatarInfo.level,
                            currentExp = homeState.avatarInfo.exp,
                            needExp = homeState.avatarInfo.needExp,
                            wearingItems = userInfoState.value.wearingItems
                        )
                        ResultRoadMapItem(
                            modifier = Modifier.fillMaxWidth()
                                .padding(horizontal = 16.gdp),
                            teacherName = userInfoState.value.teacherName.orEmpty(),
                            recreationName = userInfoState.value.recreationName
                        )
                        TeacherCheckingBox(
                            modifier = Modifier.fillMaxWidth(),
                            teacherConfirm = when {
                                homeState.roadmapProgressList.find { it.questStateInfo.questProgress == CertProgress.CHECK.code } != null -> NodeStatus.CHECK
                                homeState.roadmapProgressList.find { it.questStateInfo.questProgress == CertProgress.UPLOAD.code } != null -> NodeStatus.UPLOAD
                                else -> NodeStatus.LOCK_OPEN
                            },
                            onClickCommunity = navigateToCommunity
                        )
                        StudentRoadMapLevelBox(
                            modifier = Modifier.fillMaxWidth(),
                            roadMapLevel = when {
                                homeState.roadmapProgressList.find { it.questStateInfo.questProgress == CertProgress.CHECK.code } != null ->
                                    homeState.roadmapProgressList.find { it.questStateInfo.questProgress == CertProgress.CHECK.code }!!.getQuestTitle()
                                homeState.roadmapProgressList.find { it.questStateInfo.questProgress == CertProgress.UPLOAD.code } != null ->
                                    homeState.roadmapProgressList.find { it.questStateInfo.questProgress == CertProgress.UPLOAD.code }!!.getQuestTitle()
                                else -> ""
                            },
                        )
                        Spacer(modifier = Modifier.height(8.gdp))
                    }
                }
                Row(
                    modifier = Modifier
                        .background(GPColor.BackgroundLightGray)
                        .padding(16.gdp),
                    horizontalArrangement = Arrangement.spacedBy(8.gdp)
                ) {
                    GPButton(
                        modifier = Modifier
                            .weight(1f)
                            .height(56.gdp),
                        normalColor = GPColor.ButtonOrange,
                        pressColor = GPColor.ButtonPressOrange,
                        hoverColor = GPColor.ButtonHoverOrange,
                        onClick = navigateToJoinRoadMap,
                    ) {
                        GPText(
                            text = "진행할 로드맵 선택",
                            textSize = 14.gsp,
                            fontFamily = GPFontFamily.Bold,
                            textColor = GPColor.White
                        )
                    }
                    GPButton(
                        modifier = Modifier
                            .weight(1f)
                            .height(56.gdp),
                        normalColor = GPColor.ButtonOrange,
                        pressColor = GPColor.ButtonPressOrange,
                        hoverColor = GPColor.ButtonHoverOrange,
                        onClick = navigateToSearchRoadMap,
                    ) {
                        GPText(
                            text = "로드맵 찾아보기",
                            textSize = 14.gsp,
                            fontFamily = GPFontFamily.Bold,
                            textColor = GPColor.White
                        )
                    }
                }
            }

            if (homeState.showSuccessExerciseDialog) {
                GPSuccessRoadMapDialog(
                    questName = homeState.exerciseQuestName,
                    onDismiss = {
                        currentExerciseId = 0
                        component.dismissSuccessExerciseDialog()
                    }
                )
            }

            if (homeState.showSuccessJoggingDialog) {
                GPSuccessRoadMapDialog(
                    questName = homeState.joggingQuestName,
                    onDismiss = {
                        currentJoggingId = 0
                        component.dismissSuccessJoggingDialog()
                    }
                )
            }

            with (levelUpEffects) {
                if (this) {
                    GPLevelUpDialog(
                        level = userInfoState.value.level.toString(),
                        onDismiss = {
                            AvatarUtil.dismissLevelUpDialog()
                        }
                    )
                }
            }
        }
    }
}