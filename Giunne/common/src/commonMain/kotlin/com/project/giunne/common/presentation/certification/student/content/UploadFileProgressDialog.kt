package com.project.giunne.common.presentation.certification.student.content

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun UploadFileProgressDialog(
    progress: Float,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false
        )
    ) {
        Column(
            modifier = Modifier
                .width(320.gdp)
                .wrapContentHeight()
                .background(
                    color = GPColor.White,
                    shape = RoundedCornerShape(16.gdp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.gdp))

            GPText(
                textSize = 16.gsp,
                text = "파일 업로드 중"
            )

            Row(
                modifier = Modifier
                    .padding(16.gdp)
                    .fillMaxWidth()
                    .height(12.gdp)
                    .clip(RoundedCornerShape(16.gdp))
                    .background(GPColor.BackgroundGray_F6F6F6)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .height(12.gdp)
                        .clip(RoundedCornerShape(16.gdp))
                        .background(GPColor.MainOrangeColor)
                        .animateContentSize()
                )
            }

            GPButton(
                modifier = Modifier
                    .padding(horizontal = 16.gdp)
                    .fillMaxWidth()
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
                    textColor = GPColor.TextGray
                )
            }

            Spacer(modifier = Modifier.height(16.gdp))
        }
    }
}