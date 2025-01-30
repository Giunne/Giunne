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
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.button.GPIconButton
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_upload_video
import org.jetbrains.compose.resources.painterResource

@Composable
fun RoadmapCertBox(
    modifier: Modifier = Modifier,
    roadmapLevel: String,
    roadmapName: String,
    video: Any? = null,
    onUploadButtonClicked: () -> Unit,
    onCertButtonClicked: () -> Unit,
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
                    text = roadmapLevel,
                    textColor = GPColor.MainOrangeColor,
                    textSize = 14.gsp,
                    fontFamily = GPFontFamily.Bold
                )
                SpW(2.gdp)
                GPText(
                    text = roadmapName,
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
            if (video != null) {
                Spacer(modifier = Modifier.weight(1f))
                AsyncImage(
                    modifier = Modifier
                        .height(130.gdp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.gdp)),
                    model = "https://picsum.photos/200/300",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
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
                Column(
                    modifier = Modifier
                        .height(130.gdp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    GPIconButton(
                        modifier = Modifier.size(72.gdp),
                        icon = {
                            Image(
                                modifier = Modifier.size(52.gdp),
                                painter = painterResource(Res.drawable.icon_upload_video),
                                contentDescription = null,
                            )
                        },
                        normalColor = GPColor.White,
                        pressColor = GPColor.ButtonPressWhite,
                        onClick = {
                            onUploadButtonClicked()
                        },
                    )
                    SpH(16.gdp)
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        GPText(
                            text = "인증에 필요한 ",
                            textColor = GPColor.TextBlack,
                            textSize = 14.gsp,
                            fontFamily = GPFontFamily.Bold
                        )
                        GPText(
                            text = "동영상",
                            textColor = GPColor.MainOrangeColor,
                            textSize = 14.gsp,
                            fontFamily = GPFontFamily.Bold
                        )
                        GPText(
                            text = "을 촬영해 올려주세요",
                            textColor = GPColor.TextBlack,
                            textSize = 14.gsp,
                            fontFamily = GPFontFamily.Bold
                        )
                    }
                    SpH(6.gdp)
                    GPText(
                        text = "선생님이 확인해줄거에요!",
                        textColor = GPColor.TextBlack,
                        textSize = 14.gsp,
                        fontFamily = GPFontFamily.Bold
                    )
                }
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