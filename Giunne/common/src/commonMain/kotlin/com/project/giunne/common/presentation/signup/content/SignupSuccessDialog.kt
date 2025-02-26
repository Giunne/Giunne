package com.project.giunne.common.presentation.signup.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun SignupSuccessDialog(
    modifier: Modifier = Modifier,
    dismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = {  },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        )
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = GPColor.White,
                    shape = RoundedCornerShape(12.gdp)
                )
                .width(320.gdp)
                .wrapContentHeight()
                .padding(10.gdp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpH(24.gdp)
            GPText(
                text = "기운내 가입을 축하드립니다! 🎉",
                textSize = 14.gsp,
                textColor = GPColor.TextBlack,
                fontFamily = GPFontFamily.Bold
            )
            SpH(40.gdp)
            Row(
                modifier = Modifier
                    .height(48.gdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GPButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.gdp),
                    normalColor = GPColor.ButtonOrange,
                    pressColor = GPColor.ButtonPressOrange,
                    hoverColor = GPColor.ButtonHoverOrange,
                    onClick = { dismiss() },
                ) {
                    GPText(
                        text = "로그인 하러가기",
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.White
                    )
                }
            }
        }
    }
}