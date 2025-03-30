package com.project.giunne.common.presentation.mypage.student.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.common.textfield.GPTextFieldWithClose
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.extension.addFocusCleaner
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun ModifyStudentInfoDialog(
    modifier: Modifier = Modifier,
    focusManager: FocusManager,
    onDismiss: () -> Unit,
    nickName: String,
    grade: String,
    classNumber: String,
    onModifyInformation: (nickName: String, grade: String, classNumber: String) -> Unit
) {

    var nickNameText by remember { mutableStateOf(nickName) }
    var gradeText by remember { mutableStateOf(grade) }
    var classText by remember { mutableStateOf(classNumber) }
    val isEnabled by remember {
        derivedStateOf {
            gradeText.isNotEmpty() && classText.isNotEmpty() && nickNameText.isNotEmpty()
        }
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Column(
            modifier = modifier
                .background(
                    color = GPColor.White,
                    shape = RoundedCornerShape(12.gdp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            GPText(
                modifier = Modifier.padding(top = 32.gdp, bottom = 16.gdp),
                text = "학년 & 반을 입력해주세요.",
                textSize = 22.sp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .addFocusCleaner(focusManager)
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
                text = nickNameText,
                onTextChange = { name ->
                    nickNameText = name
                },
                placeHolder = "ex.홍길동",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Person,
                        contentDescription = "이름"
                    )
                },
                onClear = {
                    nickNameText = ""
                },
                focusManager = focusManager
            )
            Row(
                modifier = Modifier
                    .padding(16.gdp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GPButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(44.gdp),
                    normalColor = GPColor.ButtonLightGray,
                    pressColor = GPColor.ButtonPressLightGray,
                    hoverColor = GPColor.ButtonHoverLightGray,
                    onClick = onDismiss,
                ) {
                    GPText(
                        text = "닫기",
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.White
                    )
                }
                SpW(10.gdp)
                GPButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(44.gdp),
                    normalColor = if (isEnabled) GPColor.ButtonOrange else GPColor.ButtonLightGray,
                    pressColor = if (isEnabled) GPColor.ButtonPressOrange else GPColor.ButtonLightGray,
                    hoverColor = if (isEnabled) GPColor.ButtonHoverOrange else GPColor.ButtonLightGray,
                    onClick = {
                        if (isEnabled) {
                            onModifyInformation(nickNameText, gradeText, classText)
                            onDismiss()
                        }
                    },
                ) {
                    GPText(
                        text = "변경하기",
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.White
                    )
                }
            }
        }
    }
}