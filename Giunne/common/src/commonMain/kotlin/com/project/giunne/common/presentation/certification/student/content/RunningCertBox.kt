package com.project.giunne.common.presentation.certification.student.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.button.GPIconButton
import com.project.giunne.common.presentation.common.picker.PlatformFile
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_upload_image
import org.jetbrains.compose.resources.painterResource

@Composable
fun RunningCertBox(
    modifier: Modifier = Modifier,
    weekText: String,
    image: PlatformFile? = null,
    onUploadButtonClicked: () -> Unit,
    onCertButtonClicked: () -> Unit,
    onExpandButtonClicked: () -> Unit,
    onResetButtonClicked: () -> Unit,
    dragAndDropFile: (PlatformFile?) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .background(
                    color = GPColor.TextBlack,
                    shape = RoundedCornerShape(16.gdp, 0.gdp, 16.gdp, 0.gdp)
                )
                .height(32.gdp)
                .padding(horizontal = 20.gdp),
            contentAlignment = Alignment.Center
        ) {
            Row{
                GPText(
                    text = weekText,
                    textColor = GPColor.White,
                    textSize = 14.gsp,
                    fontFamily = GPFontFamily.Bold
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.gdp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (image != null) {
                Spacer(modifier = Modifier.weight(1f))
                ImagePlayBox(
                    modifier = Modifier
                        .height(130.gdp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.gdp)),
                    image = image,
                    onExpandButtonClicked = { onExpandButtonClicked() },
                    onResetButtonClicked = { onResetButtonClicked() },
                )
                SpH(16.gdp)
                GPButton(
                    modifier = Modifier
                        .width(128.gdp)
                        .height(48.gdp),
                    normalColor = GPColor.ButtonOrange,
                    pressColor = GPColor.ButtonPressOrange,
                    hoverColor = GPColor.ButtonHoverOrange,
                    onClick = { onCertButtonClicked() },
                ) {
                    GPText(
                        text = "인증하기", /* TODO String */
                        textSize = 16.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.White
                    )
                }
            } else {
                Spacer(modifier = Modifier.weight(1f))
                ImageUploadBox(
                    modifier = Modifier
                        .height(130.gdp)
                        .fillMaxWidth(),
                    onUploadButtonClicked = onUploadButtonClicked,
                    dragAndDropFile = dragAndDropFile,
                )
                SpH(16.gdp)
                Box(
                    modifier = Modifier
                        .width(128.gdp)
                        .height(48.gdp)
                        .background(
                            color = GPColor.ButtonLightGray,
                            shape = RoundedCornerShape(12.gdp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    GPText(
                        text = "인증하기", /* TODO String */
                        textSize = 16.gsp,
                        fontFamily = GPFontFamily.Bold,
                        textColor = GPColor.White
                    )
                }
            }
        }
    }
}