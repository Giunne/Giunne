package com.project.giunne.common.presentation.community.student.content

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImagePainter.State.Empty.painter
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.button.GPIconButton
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.shape.GPSquircleShape
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.community.student.dummy.CommentDto
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_delete
import com.project.giunne.icon_edit
import com.project.giunne.icon_more
import com.project.giunne.icon_upload_video
import com.project.giunne.test_character
import org.jetbrains.compose.resources.painterResource

@Composable
fun CommentItemRow(
    modifier: Modifier = Modifier,
    commentDto: CommentDto,
    onMenuButtonClicked: () -> Unit,
) {
    var isMenuOpen by remember { mutableStateOf(false) }

    val animatedDP by animateDpAsState(
        targetValue = if (isMenuOpen) (-28).gdp else 0.gdp
    )

    val animatedWidth by animateDpAsState(
        targetValue = if (isMenuOpen) 28.gdp else 0.gdp
    )

    Box(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
//                .background(GPColor.White)
                .offset(x = animatedDP),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(28.gdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GPSquircleShape(
                    modifier = Modifier
                        .size(28.gdp),
                    backgroundColor = GPColor.BackgroundGray_F6F6F6
                ) {
                    Image(
                        modifier = Modifier.size(24.gdp),
                        painter = painterResource(Res.drawable.test_character), //TODO API
                        contentDescription = null
                    )
                }
                SpW(8.gdp)
                GPText(
                    modifier = Modifier.weight(1f),
                    text = commentDto.name,
                    textColor = GPColor.TextBlack,
                    textSize = 8.gsp,
                    fontFamily = GPFontFamily.Bold
                )
                Box(
                    modifier = Modifier
                        .size(28.gdp)
                        .noRippleClickable {
                            onMenuButtonClicked()
                            isMenuOpen = !isMenuOpen
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.size(12.gdp),
                        painter = painterResource(Res.drawable.icon_more),
                        contentDescription = null,
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.gdp)
                    .heightIn(min = 28.gdp)
            ) {
                GPText(
                    modifier = Modifier,
                    text = commentDto.content,
                    textSize = 10.gsp,
                    fontFamily = GPFontFamily.Regular,
                    textColor = GPColor.TextBlack
                )
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .fillMaxHeight()
                .width(animatedWidth),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(28.gdp)
                    .background(GPColor.BackgroundFrameOrange),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.size(14.gdp),
                    painter = painterResource(Res.drawable.icon_edit),
                    contentDescription = null
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(28.gdp)
                    .background(GPColor.Red),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.size(14.gdp),
                    painter = painterResource(Res.drawable.icon_delete),
                    contentDescription = null
                )
            }
        }
    }
}