package com.project.giunne.common.presentation.home.teacher

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.buildAnnotatedString
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.home.teacher.content.RemainCheckingStudentBox
import com.project.giunne.common.presentation.home.teacher.content.StudentSignUpCodeBox
import com.project.giunne.common.presentation.home.teacher.content.TeacherCreateRoadmapDialog
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import kotlinx.coroutines.launch
import java.awt.SystemColor.text

private const val TAG = "TeacherHomeScreen"

@Composable
internal fun TeacherHomeScreen(
    component: TeacherHomeComponent,
    modifier: Modifier = Modifier,
    navigateToCommunity: () -> Unit,
) {
    GLog.d(TAG, "onCreate")

    val focusManager = LocalFocusManager.current
    val clipboardManager = LocalClipboardManager.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var roadMapName by remember { mutableStateOf("") }
    var roadMapNumber by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    val isEnabled by remember {
        derivedStateOf {
            roadMapName.isNotEmpty() && roadMapNumber.isNotEmpty()
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
                .verticalScroll(rememberScrollState())
                .addFocusCleaner(focusManager),
            verticalArrangement = Arrangement.spacedBy(16.gdp)
        ) {
            Spacer(modifier = Modifier.height(16.gdp))

            StudentSignUpCodeBox(
                modifier = Modifier
                    .fillMaxWidth(),
                signUpCode = "testcode",
                onCopyCode = {
                    clipboardManager.setText(
                        annotatedString = buildAnnotatedString {
                            append("testcode")
                        }
                    )
                    scope.launch {
                        snackbarHostState.showSnackbar("코드가 복사되었습니다.")
                    }
                }
            )
            RemainCheckingStudentBox(
                modifier = Modifier.fillMaxWidth(),
                studentCount = 12,
                onClickCommunity = navigateToCommunity
            )

            GPButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.gdp)
                    .padding(horizontal = 16.gdp),
                normalColor = GPColor.ButtonOrange,
                pressColor = GPColor.ButtonPressOrange,
                hoverColor = GPColor.ButtonHoverOrange,
                onClick = {
                    showDialog = true
                },
            ) {
                GPText(
                    text = "로그맵 생성하기",
                    textSize = 14.gsp,
                    fontFamily = GPFontFamily.Bold,
                    textColor = GPColor.White
                )
            }
            Spacer(modifier = Modifier.height(16.gdp))
        }
        if (showDialog) {
            TeacherCreateRoadmapDialog(
                focusManager = focusManager,
                isEnabled = isEnabled,
                roadMapName = roadMapName,
                roadMapNumber = roadMapNumber,
                onTitleChange = {
                    roadMapName = it
                },
                onNumberChange = { numberString ->
                    if (numberString.all { it.isDigit() }) {
                        roadMapNumber = numberString
                    }
                },
                onClearTitle = {
                    roadMapName = ""
                },
                onClearNumber = {
                    roadMapNumber = ""
                },
                onDismiss = {
                    showDialog = false
                },
                onConfirm = {

                }
            )
        }
    }
}
