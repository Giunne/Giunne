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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.home.common.EmptyResult
import com.project.giunne.common.presentation.home.student.content.ResultRoadMapItem
import com.project.giunne.common.presentation.home.student.content.StudentCharacter
import com.project.giunne.common.presentation.home.student.content.StudentRoadMapLevelBox
import com.project.giunne.common.presentation.home.student.content.TeacherCheckingBox
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.Define
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
    navigateToCommunity: () -> Unit,
    navigateToSearchRoadMap: () -> Unit
) {
    GLog.d(TAG, "onCreate")

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .addFocusCleaner(focusManager)
            .fillMaxSize()
            .background(GPColor.BackgroundLightGray)
            .imePadding(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (Define.playerId == 1) {
                EmptyResult(
                    modifier = Modifier.weight(1f),
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
                        level = 3,
                        currentExp = 6,
                        totalExp = 10,
                        character = Res.drawable.test_character,
                        items = listOf()
                    )
                    ResultRoadMapItem(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 16.gdp),
                        teacherName = "홍길동",
                        description = "Test"
                    )
                    TeacherCheckingBox(
                        modifier = Modifier.fillMaxWidth(),
                        /*TODO(나중에 API나오면 상태에 따라 문구 변경)*/
                        teacherStateTitle = "확인중",
                        onClickCommunity = navigateToCommunity
                    )
                    StudentRoadMapLevelBox(
                        modifier = Modifier.fillMaxWidth(),
                        /*TODO(나중에 API나오면 상태에 따라 문구 변경)*/
                        roadMapLevel = "3"
                    )
                    Spacer(modifier = Modifier.height(8.gdp))
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
                        onClick = {},
                    ) {
                        GPText(
                            text = "로드맵 선택",
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

        }
    }
}