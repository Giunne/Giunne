package com.project.giunne.common.presentation.notification.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.shape.GPSquircleShape
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.main.dummy.Noti
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_badge
import org.jetbrains.compose.resources.painterResource

@Composable
fun NotificationItem(
    modifier: Modifier = Modifier,
    notificationInfo: Noti
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
                text = "공지",
                textSize = 10.gsp,
                fontFamily = GPFontFamily.Medium,
                textColor = GPColor.ButtonLightGray
            )
            SpH(6.gdp)
            GPText(
                text = notificationInfo.content,
                textSize = 12.gsp,
                fontFamily = GPFontFamily.Medium,
                textColor = GPColor.TextBlack
            )
        }
        Box(
            modifier = Modifier
                .height(20.gdp)
                .align(Alignment.Top)
        ){
            GPText(
                modifier = Modifier.align(Alignment.BottomEnd),
                text = notificationInfo.time,
                textSize = 10.gsp,
                fontFamily = GPFontFamily.Medium,
                textColor = GPColor.ButtonLightGray
            )
            if (!notificationInfo.isRead) {
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