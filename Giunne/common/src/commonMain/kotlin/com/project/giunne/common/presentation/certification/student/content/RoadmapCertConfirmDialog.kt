package com.project.giunne.common.presentation.certification.student.content

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
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import kotlinx.serialization.json.JsonNull.content

@Composable
fun RoadmapCertConfirmDialog(
    modifier: Modifier = Modifier,
    onDismissButtonClicked: () -> Unit,
    onConfirmButtonClicked: () -> Unit,
    levelText: String
) {
    Dialog(
        onDismissRequest = { onDismissButtonClicked() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        )
    ) {
        Card(
            modifier = Modifier
                .background(GPColor.White, RoundedCornerShape(12.gdp))
                .width(320.gdp)
                .wrapContentHeight()
                .padding(16.gdp),
            shape = RoundedCornerShape(12.gdp)
        ) {
            Column(
                modifier = Modifier
                    .background(GPColor.White)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.gdp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    GPText(
                        text = levelText,
                        textSize = 18.gsp,
                        textColor = GPColor.BackgroundFrameOrange,
                        fontFamily = GPFontFamily.Bold
                    )
                    GPText(
                        text = "인증 확인",
                        textSize = 18.gsp,
                        textColor = GPColor.ButtonBlack,
                        fontFamily = GPFontFamily.Bold
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(vertical = 20.gdp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    GPText(
                        text = "인증하려는 동영상이 맞는지 다시 확인해주세요!",
                        textSize = 12.gsp,
                        textColor = GPColor.TextGray,
                        fontFamily = GPFontFamily.Regular
                    )
                }
                Row(
                    modifier = Modifier
                        .height(48.gdp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GPButton(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.gdp),
                        normalColor = GPColor.ButtonLightGray,
                        pressColor = GPColor.ButtonPressLightGray,
                        hoverColor = GPColor.ButtonHoverLightGray,
                        onClick = { onDismissButtonClicked() },
                    ) {
                        GPText(
                            text = "한번 더 확인",
                            textSize = 14.gsp,
                            fontFamily = GPFontFamily.Bold,
                            textColor = GPColor.TextGray
                        )
                    }
                    SpW(10.gdp)
                    GPButton(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.gdp),
                        normalColor = GPColor.ButtonOrange,
                        pressColor = GPColor.ButtonPressOrange,
                        hoverColor = GPColor.ButtonHoverOrange,
                        onClick = { onConfirmButtonClicked() },
                    ) {
                        GPText(
                            text = "인증하기",
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