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
import androidx.compose.material.icons.rounded.Person
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.dialog.GPAlertDialog
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.common.textfield.GPTextFieldWithClose
import com.project.giunne.common.presentation.select.content.SelectCharacterPage
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.extension.addFocusCleaner
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun StudentCharacterSelectScreen(
    component: StudentSelectCharacterComponent,
    recreationId: Int,
    onBackClick: () -> Unit,
    navigateToHome: () -> Unit
) {
    val selectCharacterState by component.uiState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(
        pageCount = { selectCharacterState.characterList.size }
    )
    val focusManager = LocalFocusManager.current
    var gradeText by remember { mutableStateOf("") }
    var classText by remember { mutableStateOf("") }
    var nameText by remember { mutableStateOf("") }
    val isEnabled by remember {
        derivedStateOf {
            gradeText.isNotEmpty() && classText.isNotEmpty() && nameText.isNotEmpty()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GPColor.BackgroundLightGray)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .background(GPColor.BackgroundLightGray)
                .addFocusCleaner(focusManager)
                .verticalScroll(rememberScrollState())
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            GPText(
                modifier = Modifier.padding(top = 64.gdp, bottom = 32.gdp),
                text = "시작 캐릭터를 선택해주세요",
                textSize = 24.sp
            )

            SelectCharacterPage(
                modifier = Modifier.size(200.gdp),
                pagerState,
                selectCharacterState.characterList
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
                    onTextChange = { grade ->
                        if (grade.all { it.isDigit() }) {
                            gradeText = grade
                        }
                    },
                    placeHolder = "ex.1(학년)",
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Face,
                            contentDescription = "학년"
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
                    onTextChange = { classNo ->
                        if (classNo.all { it.isDigit() }) {
                            classText = classNo
                        }
                    },
                    placeHolder = "ex.1(반)",
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Face,
                            contentDescription = "반"
                        )
                    },
                    onClear = {
                        classText = ""
                    },
                    focusManager = focusManager
                )
            }

            GPText(
                modifier = Modifier.padding(top = 32.gdp, bottom = 16.gdp),
                text = "이름을 입력해주세요.",
                textSize = 22.sp
            )

            GPTextFieldWithClose(
                modifier = Modifier
                    .padding(horizontal = 16.gdp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = nameText,
                onTextChange = { name ->
                    nameText = name
                },
                placeHolder = "ex.홍길동",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Person,
                        contentDescription = "이름"
                    )
                },
                onClear = {
                    nameText = ""
                },
                focusManager = focusManager
            )

            Spacer(modifier = Modifier.height(16.gdp))
        }
        GPButton(
            modifier = Modifier
                .padding(16.gdp)
                .fillMaxWidth()
                .height(56.gdp),
            normalColor = if (isEnabled) GPColor.ButtonOrange else GPColor.ButtonLightGray,
            pressColor = if (isEnabled) GPColor.ButtonPressOrange else GPColor.ButtonLightGray,
            hoverColor = if (isEnabled) GPColor.ButtonHoverOrange else GPColor.ButtonLightGray,
            onClick = {
                if (isEnabled) {
                    component.createAvatar(
                        recreationId = recreationId,
                        characterNo = selectCharacterState.characterList[pagerState.currentPage].id,
                        grade = gradeText.toInt(),
                        classNo = classText.toInt(),
                        name = nameText
                    )
                }
            },
        ) {
            GPText(
                text = "시작하기",
                textSize = 14.gsp,
                fontFamily = GPFontFamily.Bold,
                textColor = GPColor.White
            )
        }
    }

    if (selectCharacterState.successJoinDialog) {
        GPAlertDialog(
            dismiss = {
                component.dismissSuccessJoinDialog()
                navigateToHome()
            },
            title = "로드맵에 가입",
            content = "성공적으로 가입되었습니다!",
        )
    }

    with(selectCharacterState.error) {
        if (this != null) {
            GPAlertDialog(
                dismiss = { component.dismissErrorDialog() },
                title = "로드맵 가입 에러",
                content = selectCharacterState.error?.message.orEmpty(),
            )
        }
    }

}