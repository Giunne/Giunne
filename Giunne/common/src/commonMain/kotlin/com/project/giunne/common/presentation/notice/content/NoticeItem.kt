package com.project.giunne.common.presentation.notice.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import com.project.giunne.Res
import com.project.giunne.common.data.remote.response.NoticeResponse
import com.project.giunne.common.data.remote.response.NoticeResponse.Companion.formatTimeAgo
import com.project.giunne.common.presentation.common.shape.GPSquircleShape
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_badge
import org.jetbrains.compose.resources.painterResource

@Composable
fun NoticeItem(
    modifier: Modifier = Modifier,
    noticeData: NoticeResponse
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        GPSquircleShape(
            modifier = Modifier.size(24.gdp),
            backgroundColor = GPColor.BackgroundFrameOrange,
            content = {
                Image(
                    modifier = Modifier.size(16.gdp),
                    painter = painterResource(Res.drawable.icon_badge),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(GPColor.White)
                )
            }
        )
        SpW(16.gdp)
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            GPText(
                text = noticeData.title,
                textSize = 10.gsp,
                fontFamily = GPFontFamily.Medium,
                textColor = GPColor.ButtonLightGray
            )
            SpH(6.gdp)
            GPText(
                text = noticeData.content,
                textSize = 12.gsp,
                fontFamily = GPFontFamily.Medium,
                textColor = GPColor.TextBlack,
                maxLines = 1,
            )
        }
        Box(
            modifier = Modifier
                .height(20.gdp)
                .align(Alignment.Top)
        ){
            GPText(
                modifier = Modifier.align(Alignment.BottomEnd),
                text = noticeData.createTime.formatTimeAgo(),
                textSize = 10.gsp,
                fontFamily = GPFontFamily.Medium,
                textColor = GPColor.ButtonLightGray
            )
            if (true) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(6.gdp)
                        .background(GPColor.BackgroundFrameOrange, CircleShape),
                )
            }
        }
    }
}