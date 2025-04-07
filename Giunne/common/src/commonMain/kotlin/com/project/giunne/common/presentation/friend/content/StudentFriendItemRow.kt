package com.project.giunne.common.presentation.friend.content

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.data.remote.response.WearingItem
import com.project.giunne.common.presentation.common.charactor.GPSmallCharacter
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.shape.GPSquircleShape
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun StudentFriendItemRow(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    friendInfo: AvatarUserResponse,
    onClick: (List<WearingItem>) -> Unit
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val fillColor by animateColorAsState(
        targetValue = when {
            isPressed -> GPColor.ButtonPressWhite
            isHovered -> GPColor.ButtonHoverWhite
            else -> GPColor.White
        }
    )

    Row(
        modifier = modifier
            .background(
                color = fillColor,
                shape = RoundedCornerShape(12.gdp)
            )
            .padding(horizontal = 8.gdp)
            .noRippleClickable(interactionSource = interactionSource) {
                onClick(friendInfo.wearingItems)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        GPText(
            modifier = Modifier
                .padding(horizontal = 4.gdp),
            text = friendInfo.ranking.toString(),
            textSize = 12.gsp,
            fontFamily = GPFontFamily.Bold,
            textColor = GPColor.TextBlack
        )
        GPSquircleShape(
            modifier = Modifier.size(52.gdp),
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
        Column(
            horizontalAlignment = Alignment.End
        ) {
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
            SpH(2.gdp)
            Row {
                GPText(
                    text = friendInfo.exp.toString(),
                    textSize = 12.gsp,
                    fontFamily = GPFontFamily.Medium,
                    textColor = GPColor.MainOrangeColor
                )
                GPText(
                    text = " exp",
                    textSize = 12.gsp,
                    fontFamily = GPFontFamily.Medium,
                    textColor = GPColor.TextBlack
                )
            }
        }
    }
}