package com.project.giunne.common.presentation.friend.content

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.presentation.common.charactor.GPSmallCharacter
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.shape.GPSquircleShape
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun TeacherFriendItemRow(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    friendInfo: AvatarUserResponse,
    onClick: (Int) -> Unit
) {

    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val fillColor by animateColorAsState(
        targetValue = when {
            isPressed -> GPColor.ButtonPressWhite
            isHovered -> GPColor.ButtonHoverWhite
            else -> GPColor.BackgroundLightGray
        },
    )

    Row(
        modifier = modifier
            .background(
                color = fillColor,
                shape = RoundedCornerShape(12.gdp)
            )
            .padding(8.gdp)
            .noRippleClickable(interactionSource = interactionSource) {
                onClick(friendInfo.id)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        GPSquircleShape(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f),
            backgroundColor = GPColor.BackgroundFrameOrange,
            content = {
                GPSmallCharacter(
                    modifier = Modifier.fillMaxSize(),
                    wearingItems = friendInfo.wearingItems
                )
            }
        )
        SpW(16.gdp)
        GPText(
            modifier = Modifier.weight(1f),
            text = friendInfo.nickname,
            textSize = 14.gsp,
            fontFamily = GPFontFamily.Medium,
            textColor = GPColor.TextBlack
        )
        Row{
            GPText(
                text = "레벨 ",
                textSize = 12.gsp,
                fontFamily = GPFontFamily.Medium,
                textColor = GPColor.TextBlack
            )
            GPText(
                text = friendInfo.level.toString(),
                textSize = 12.gsp,
                fontFamily = GPFontFamily.Medium,
                textColor = GPColor.MainOrangeColor
            )
        }
    }
}