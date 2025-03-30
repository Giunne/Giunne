package com.project.giunne.common.presentation.friend.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.project.giunne.common.data.remote.response.StudentCourseInfo
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun StudentRoadMapDialog(
    pageType: CertPage,
    questInfoList: List<StudentCourseInfo>,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GPColor.BackgroundLightGray),
            verticalArrangement = Arrangement.spacedBy(8.gdp)
        ) {

            when (pageType) {
                CertPage.RoadMap -> {
                    StudentExerciseView(
                        modifier = Modifier.weight(1f),
                        questInfoList = questInfoList
                    )
                }

                CertPage.Running -> {
                    StudentJoggingView(
                        modifier = Modifier.weight(1f),
                        questInfoList = questInfoList
                    )
                }
            }

            GPButton(
                modifier = Modifier
                    .background(GPColor.BackgroundLightGray)
                    .padding(16.gdp)
                    .height(48.gdp)
                    .fillMaxWidth(),
                normalColor = GPColor.ButtonLightGray,
                pressColor = GPColor.ButtonPressLightGray,
                hoverColor = GPColor.ButtonHoverLightGray,
                onClick = onDismiss,
            ) {
                GPText(
                    text = "닫기",
                    textSize = 14.gsp,
                    fontFamily = GPFontFamily.Bold,
                    textColor = GPColor.TextBlack
                )
            }
        }
    }

}