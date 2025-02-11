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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter.State.Empty.painter
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.button.GPIconButton
import com.project.giunne.common.presentation.common.progress.GPCertCircleProgress
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_upload_video
import com.project.giunne.roadcon_3_beast
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun RunningCertProgressBox(
    modifier: Modifier = Modifier,
    weekText: String,
    progressText: String,
    icon: @Composable () -> Unit = {  },
    step: Int,
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
                .wrapContentWidth()
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
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(140.gdp),
                contentAlignment = Alignment.Center
            ) {
                GPCertCircleProgress(
                    modifier = Modifier.fillMaxSize(),
                    percent = (step.toFloat() / 2)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    icon()
                    SpH(4.gdp)
                    GPText(
                        text = "50%", /* TODO API */
                        textSize = 18.gsp,
                        textColor = GPColor.TextBlack,
                        fontFamily = GPFontFamily.Bold
                    )
                }
            }
            SpH(24.gdp)
            GPText(
                text = progressText,
                textSize = 18.gsp,
                fontFamily = GPFontFamily.Bold,
                textColor = GPColor.TextBlack
            )
        }
    }
}