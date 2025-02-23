package com.project.giunne.common.presentation.friend.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.presentation.common.shape.GPSquircleShape
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.friend.dummy.Avatar
import com.project.giunne.common.presentation.friend.dummy.FriendInfo
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import org.jetbrains.compose.resources.painterResource

@Composable
fun TeacherFriendItemRow(
    modifier: Modifier = Modifier,
    friendInfo: FriendInfo // TODO API
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        GPSquircleShape(
            modifier = Modifier.size(52.gdp),
            backgroundColor = GPColor.BackgroundFrameOrange,
            content = {
                Image(
                    painter = painterResource(friendInfo.avatar.avatar.img),
                    contentDescription = null
                )
            }
        )
        SpW(16.gdp)
        GPText(
            modifier = Modifier.weight(1f),
            text = friendInfo.avatar.name,
            textSize = 14.gsp,
            fontFamily = GPFontFamily.Medium,
            textColor = GPColor.TextBlack
        )
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            Row{
                GPText(
                    text = "레벨 ",
                    textSize = 12.gsp,
                    fontFamily = GPFontFamily.Medium,
                    textColor = GPColor.TextBlack
                )
                GPText(
                    text = friendInfo.avatar.level.toString(),
                    textSize = 12.gsp,
                    fontFamily = GPFontFamily.Medium,
                    textColor = GPColor.MainOrangeColor
                )
            }
            SpH(2.gdp)
            Row{
                GPText(
                    text = "로드맵 ",
                    textSize = 12.gsp,
                    fontFamily = GPFontFamily.Medium,
                    textColor = GPColor.TextBlack
                )
                GPText(
                    text = friendInfo.roadmapProcess,
                    textSize = 12.gsp,
                    fontFamily = GPFontFamily.Medium,
                    textColor = GPColor.MainOrangeColor
                )
            }
            SpH(2.gdp)
            Row{
                GPText(
                    text = "러닝 ",
                    textSize = 12.gsp,
                    fontFamily = GPFontFamily.Medium,
                    textColor = GPColor.TextBlack
                )
                GPText(
                    text = friendInfo.runningProcess,
                    textSize = 12.gsp,
                    fontFamily = GPFontFamily.Medium,
                    textColor = GPColor.MainOrangeColor
                )
            }
        }
    }
}