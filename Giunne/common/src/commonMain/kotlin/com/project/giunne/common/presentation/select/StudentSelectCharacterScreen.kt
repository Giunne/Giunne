package com.project.giunne.common.presentation.select

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.sp
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.common.textfield.GPTextFieldWithClose
import com.project.giunne.common.presentation.select.content.SelectCharacterPage
import com.project.giunne.common.presentation.select.state.characterList
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.extension.addFocusCleaner
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun StudentCharacterSelectScreen(
    component: StudentSelectCharacterComponent
) {
    val pagerState = rememberPagerState(
        pageCount = { characterList.size }
    )
    val focusManager = LocalFocusManager.current
    var gradeText by remember { mutableStateOf("") }
    var classText by remember { mutableStateOf("") }
    val isEnabled by remember {
        derivedStateOf {
            gradeText.isNotEmpty() && classText.isNotEmpty()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GPColor.BackgroundLightGray)
            .addFocusCleaner(focusManager)
            .verticalScroll(rememberScrollState())
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        GPText(
            modifier = Modifier.padding(top = 64.gdp, bottom = 16.gdp),
            text = "시작 캐릭터를 선택해주세요",
            textSize = 22.sp
        )

        SelectCharacterPage(
            modifier = Modifier.size(200.gdp),
            pagerState,
            characterList
        )

        GPText(
            modifier = Modifier.padding(top = 32.gdp, bottom = 16.gdp),
            text = "학년 & 반을 입력해주세요.",
            textSize = 22.sp
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.gdp),
            horizontalArrangement = Arrangement.spacedBy(8.gdp)
        ) {
            GPTextFieldWithClose(
                modifier = Modifier.weight(1f),
                text = gradeText,
                onTextChange = {
                    gradeText = it
                },
                placeHolder = "ex.1학년",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Face,
                        contentDescription = "검색"
                    )
                },
                onClear = {
                    gradeText = ""
                },
                focusManager = focusManager
            )

            GPTextFieldWithClose(
                modifier = Modifier.weight(1f),
                text = classText,
                onTextChange = {
                    classText = it
                },
                placeHolder = "ex.1반",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Face,
                        contentDescription = "검색"
                    )
                },
                onClear = {
                    classText = ""
                },
                focusManager = focusManager
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        GPButton(
            modifier = Modifier
                .padding(vertical = 8.gdp)
                .fillMaxWidth()
                .height(56.gdp)
                .padding(horizontal = 16.gdp),
            normalColor = if (isEnabled) GPColor.ButtonOrange else GPColor.ButtonLightGray,
            pressColor = if (isEnabled) GPColor.ButtonPressOrange else GPColor.ButtonLightGray,
            hoverColor = if (isEnabled) GPColor.ButtonHoverOrange else GPColor.ButtonLightGray,
            onClick = { },
        ) {
            GPText(
                text = "선택하기",
                textSize = 14.gsp,
                fontFamily = GPFontFamily.Bold,
                textColor = GPColor.White
            )
        }
    }
}