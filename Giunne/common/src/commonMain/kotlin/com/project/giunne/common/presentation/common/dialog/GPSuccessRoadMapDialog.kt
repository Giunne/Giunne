package com.project.giunne.common.presentation.common.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.text.GPAnnotatedText
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.shop.content.LottieBox
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun GPSuccessRoadMapDialog(
    onDismiss: () -> Unit,
    questName: String
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.gdp)
                .background(
                    color = GPColor.White,
                    shape = RoundedCornerShape(16.gdp)
                )
                .padding(16.gdp)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.gdp)
        ) {
            LottieBox(
                modifier = Modifier.size(128.gdp),
                filePath = "files/animation_success.json"
            )

            GPAnnotatedText(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = GPColor.MainOrangeColor,
                            fontSize = 16.gsp
                        )
                    ) {
                        append(questName)
                    }
                    append(" 완료했습니다! 🥳")
                },
                textSize = 16.gsp,
            )

            GPButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.gdp),
                normalColor = GPColor.ButtonOrange,
                pressColor = GPColor.ButtonPressOrange,
                hoverColor = GPColor.ButtonHoverOrange,
                onClick = onDismiss,
            ) {
                GPText(
                    text = "닫기",
                    textSize = 14.gsp,
                    fontFamily = GPFontFamily.Bold,
                    textColor = GPColor.White
                )
            }
        }
    }
}