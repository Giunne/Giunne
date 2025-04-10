package com.project.giunne.common.presentation.notice.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import com.project.giunne.Res
import com.project.giunne.common.data.remote.response.NoticeResponse
import com.project.giunne.common.data.remote.response.NoticeResponse.Companion.formatTimeAgo
import com.project.giunne.common.presentation.common.noRippleClickable
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
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    noticeData: NoticeResponse,
    onClick: (Int) -> Unit
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    Row(
        modifier = modifier
            .background(
                color = when {
                    isPressed -> GPColor.BackgroundPressGray
                    isHovered -> GPColor.BackgroundHoverGray
                    else -> GPColor.BackgroundLightGray
                }
            )
            .pointerHoverIcon(icon = PointerIcon.Hand)
            .noRippleClickable(
                interactionSource = interactionSource
            ) {
                onClick(noticeData.id)
            }
            .padding(vertical = 8.gdp, horizontal = 16.gdp),
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
                text = if (noticeData.isUpdated) "업데이트 됨" + noticeData.createTime.formatTimeAgo() else noticeData.createTime.formatTimeAgo(),
                textSize = 10.gsp,
                fontFamily = GPFontFamily.Medium,
                textColor = GPColor.ButtonLightGray
            )
            if (noticeData.isRead) {
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