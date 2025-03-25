package com.project.giunne.common.presentation.roadmap.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun ExerciseActionButtons(
    modifier: Modifier = Modifier,
    isEditable: Boolean = false,
    progressState: String = "",
    onClose: () -> Unit = {},
    onConfirm: (() -> Unit)? = {},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.gdp)
    ) {
        GPButton(
            modifier = Modifier
                .height(48.gdp)
                .weight(1f),
            normalColor = GPColor.ButtonLightGray,
            pressColor = GPColor.ButtonPressLightGray,
            hoverColor = GPColor.ButtonHoverLightGray,
            onClick = { onClose() },
        ) {
            GPText(
                text = "닫기",
                textSize = 14.gsp,
                fontFamily = GPFontFamily.Bold,
                textColor = GPColor.TextBlack
            )
        }

        if (onConfirm != null) {
            GPButton(
                modifier = Modifier
                    .height(48.gdp)
                    .weight(1f),
                normalColor = GPColor.ButtonOrange,
                pressColor = GPColor.ButtonPressOrange,
                hoverColor = GPColor.ButtonHoverOrange,
                onClick = { onConfirm() },
            ) {
                GPText(
                    text = if (isEditable) "수정하기" else "인증완료",
                    textSize = 14.gsp,
                    fontFamily = GPFontFamily.Bold,
                    textColor = GPColor.White
                )
            }
        } else {
            GPButton(
                modifier = Modifier
                    .height(48.gdp)
                    .weight(1f),
                normalColor = GPColor.ButtonLightGray,
                pressColor = GPColor.ButtonPressLightGray,
                hoverColor = GPColor.ButtonHoverLightGray,
                onClick = {  },
            ) {
                GPText(
                    text = when (progressState) {
                        "LOCK_OPEN" -> "체크 전"
                        "UPLOAD" -> "확인 중"
                        "CONFIRM" -> "인증완료"
                        else -> ""
                    },
                    textSize = 14.gsp,
                    fontFamily = GPFontFamily.Bold,
                    textColor = GPColor.White
                )
            }
        }
    }
}