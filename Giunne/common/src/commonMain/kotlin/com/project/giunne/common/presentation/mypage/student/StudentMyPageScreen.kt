package com.project.giunne.common.presentation.mypage.student

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.project.giunne.common.presentation.common.charactor.GPCharacter
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.mypage.student.content.MyPageCharacter
import com.project.giunne.common.presentation.mypage.student.content.MyPageStudentInfoColumn
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GLog
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.test_character

private const val TAG = "StudentMyPageScreen"
@Composable
internal fun StudentMyPageScreen(
    component: StudentMyPageComponent,
    modifier: Modifier = Modifier,
) {
    GLog.d(TAG, "onCreate")

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .addFocusCleaner(focusManager)
            .fillMaxSize()
            .imePadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.gdp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GPCharacter(
                modifier = Modifier.size(256.gdp)
            )

            MyPageCharacter(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.gdp),
                nextLevelCharacter = Res.drawable.test_character,
                level = 3,
                percent = 0.65f
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
                    normalColor = GPColor.ButtonLightGray,
                    pressColor = GPColor.ButtonPressLightGray,
                    hoverColor = GPColor.ButtonHoverLightGray,
                    onClick = { /*TODO(상점 들어가기)*/ },
                ) {
                    GPText(
                        text = "상점",
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
                    onClick = { /*TODO(꾸미기)*/ },
                ) {
                    GPText(
                        text = "꾸미기",
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.White
                    )
                }
            }

            MyPageStudentInfoColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.gdp),
                onClickLogOut = { }
            )
            Spacer(modifier = Modifier.height(16.gdp))
        }
    }
}