package com.project.giunne.common.presentation.friend.content

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.data.remote.response.AvatarUserResponse
import com.project.giunne.common.presentation.common.charactor.GPMainCharacter
import com.project.giunne.common.presentation.common.charactor.GPSmallCharacter
import com.project.giunne.common.presentation.common.shape.GPSquircleShape
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun StudentFriendItemRow(
    modifier: Modifier = Modifier,
    friendInfo: AvatarUserResponse
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
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