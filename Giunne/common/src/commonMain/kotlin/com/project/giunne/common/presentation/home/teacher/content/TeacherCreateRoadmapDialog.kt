package com.project.giunne.common.presentation.home.teacher.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.common.textfield.GPTextFieldWithClose
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun TeacherCreateRoadmapDialog(
    focusManager: FocusManager,
    isEnabled: Boolean,
    roadMapName: String,
    roadMapNumber: String,
    onTitleChange: (String) -> Unit,
    onNumberChange: (String) -> Unit,
    onClearTitle: () -> Unit,
    onClearNumber: () -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnClickOutside = false
        )
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = GPColor.White,
                    shape = RoundedCornerShape(16.gdp)
                )
                .padding(16.gdp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.gdp)
        ) {

            GPText(
                text = "로드맵 제목",
                textSize = 16.gsp
            )

            GPTextFieldWithClose(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = roadMapName,
                onTextChange = {
                    onTitleChange(it)
                },
                placeHolder = "ex.@학년 @반~",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Face,
                        contentDescription = "로드맵 생성"
                    )
                },
                onClear = onClearTitle,
                focusManager = focusManager
            )

            GPText(
                text = "로드맵 기수",
                textSize = 16.gsp
            )

            GPTextFieldWithClose(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                text = roadMapNumber,
                onTextChange = {
                    onNumberChange(it)
                },
                placeHolder = "ex.1",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Face,
                        contentDescription = "로드맵 생성"
                    )
                },
                onClear = onClearNumber,
                focusManager = focusManager
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.gdp)
            ) {
                GPButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.gdp),
                    normalColor = GPColor.ButtonLightGray,
                    pressColor = GPColor.ButtonLightGray,
                    hoverColor = GPColor.ButtonLightGray,
                    onClick = onDismiss,
                ) {
                    GPText(
                        text = "나가기",
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.White
                    )
                }
                GPButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.gdp),
                    normalColor = if (isEnabled) GPColor.ButtonOrange else GPColor.ButtonLightGray,
                    pressColor = if (isEnabled) GPColor.ButtonPressOrange else GPColor.ButtonLightGray,
                    hoverColor = if (isEnabled) GPColor.ButtonHoverOrange else GPColor.ButtonLightGray,
                    onClick = {
                        if (isEnabled) {
                            onConfirm()
                        }
                    },
                ) {
                    GPText(
                        text = "로드맵 생성",
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.White
                    )
                }
            }
        }
    }
}