package com.project.giunne.common.presentation.home.student

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.home.student.content.StudentCharacter
import com.project.giunne.common.presentation.home.student.content.StudentRoadMapLevelBox
import com.project.giunne.common.presentation.home.student.content.TeacherCheckingBox
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.test_character

private const val TAG = "StudentRoadmapScreen"
@Composable
internal fun StudentHomeScreen(
    component: StudentHomeComponent,
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
        Column (
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.gdp)
        ) {
            StudentCharacter(
                level = 3,
                percent = 0.6f,
                character = Res.drawable.test_character,
                items = listOf()
            )
            TeacherCheckingBox(
                modifier = Modifier.fillMaxWidth(),
                teacherStateTitle = "확인중"
            )
            StudentRoadMapLevelBox(
                modifier = Modifier.fillMaxWidth(),
                roadMapLevel = "3"
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.gdp)
            ) {
                GPButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(56.gdp),
                    normalColor = GPColor.ButtonOrange,
                    pressColor = GPColor.ButtonPressOrange,
                    hoverColor = GPColor.ButtonHoverOrange,
                    onClick = { /*TODO(친구들 보러가기)*/ },
                ) {
                    GPText(
                        text = "친구들 보러가기",
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.White
                    )
                }
                Spacer(modifier = Modifier.width(8.gdp))
                GPButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(56.gdp),
                    normalColor = GPColor.ButtonOrange,
                    pressColor = GPColor.ButtonPressOrange,
                    hoverColor = GPColor.ButtonHoverOrange,
                    onClick = { /*TODO(로그맵 보러가기)*/ },
                ) {
                    GPText(
                        text = "로그맵 보러가기",
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.gdp))
        }
    }
}