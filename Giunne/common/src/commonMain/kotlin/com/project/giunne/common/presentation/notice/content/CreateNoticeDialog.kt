package com.project.giunne.common.presentation.notice.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun CreateNoticeDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onConfirm: (title: String, content: String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    val isEnabled by derivedStateOf {
        title.isNotEmpty() && content.isNotEmpty()
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Column(
            modifier = modifier
                .background(
                    color = GPColor.White,
                    shape = RoundedCornerShape(12.gdp)
                )
                .padding(16.gdp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpH(16.gdp)
            GPText(
                text = "공지사항 제목",
                textSize = 18.gsp,
                fontFamily = GPFontFamily.Bold,
                textColor = GPColor.TextBlack
            )
            SpH(8.gdp)
            NoticeTextField(
                focusManager = focusManager,
                placeholder = "공지사항 제목을 입력해주세요.",
                text = title,
                onTextChanged = {
                    title = it
                }
            )
            SpH(16.gdp)
            GPText(
                text = "공지사항 내용",
                textSize = 18.gsp,
                fontFamily = GPFontFamily.Bold,
                textColor = GPColor.TextBlack
            )
            SpH(8.gdp)
            NoticeTextField(
                focusManager = focusManager,
                placeholder = "공지사항 내용을 입력해주세요.",
                text = content,
                onTextChanged = {
                    content = it
                }
            )
            SpH(16.gdp)
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GPButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.gdp),
                    normalColor = GPColor.ButtonLightGray,
                    pressColor = GPColor.ButtonPressLightGray,
                    hoverColor = GPColor.ButtonHoverLightGray,
                    onClick = onDismiss,
                ) {
                    GPText(
                        text = "취소",
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.White
                    )
                }
                SpW(10.gdp)
                GPButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.gdp),
                    normalColor = if (isEnabled) GPColor.ButtonOrange else GPColor.ButtonLightGray,
                    pressColor = if (isEnabled) GPColor.ButtonPressOrange else GPColor.ButtonPressLightGray,
                    hoverColor = if (isEnabled) GPColor.ButtonHoverOrange else GPColor.ButtonHoverLightGray,
                    onClick = {
                        if (isEnabled) {
                            onConfirm(title, content)
                        }
                    },
                ) {
                    GPText(
                        text = "생성",
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.White
                    )
                }
            }
        }
    }
}