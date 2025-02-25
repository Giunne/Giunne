package com.project.giunne.common.presentation.community.content

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import com.project.giunne.Res
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.shape.GPSquircleShape
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_chat
import com.project.giunne.icon_roadmap
import com.project.giunne.icon_running
import org.jetbrains.compose.resources.painterResource

@Composable
fun CommunityItemRow(
    name: String,
    painter: Painter,
    date: String,
    commentCount: Int,
    content: String,
    type: CertPage,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val fillColor by animateColorAsState(
        targetValue = when {
            isPressed -> GPColor.ButtonPressWhite
            isHovered -> GPColor.ButtonHoverWhite
            else -> GPColor.White
        },
    )

    Row(
        modifier = Modifier
            .padding(vertical = 4.gdp)
            .fillMaxWidth()
            .height(76.gdp)
            .background(
                color = fillColor,
                shape = RoundedCornerShape(12.gdp)
            )
            .padding(horizontal = 8.gdp)
            .noRippleClickable(interactionSource = interactionSource) { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        GPSquircleShape(
            modifier = Modifier
                .size(56.gdp),
            backgroundColor = GPColor.BackgroundLightGray
        ) {
            Image(
                modifier = Modifier.size(48.gdp),
                painter = painter,
                contentDescription = null
            )
        }
        SpW(8.gdp)
        GPText(
            text = name,
            textColor = GPColor.TextBlack_232323,
            textSize = 14.gsp,
            fontFamily = GPFontFamily.Regular
        )
        SpW(16.gdp)
        GPText(
            modifier = Modifier.weight(1f),
            text = content,
            textColor = GPColor.TextBlack,
            textSize = 14.gsp,
            fontFamily = GPFontFamily.Bold
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 8.gdp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(12.gdp),
                    painter = when(type) {
                        CertPage.RoadMap -> { painterResource(Res.drawable.icon_roadmap) }
                        else -> { painterResource(Res.drawable.icon_running) }
                    },
                    colorFilter = ColorFilter.tint(GPColor.TextLightGray),
                    contentDescription = null
                )
                SpW(2.gdp)
                GPText(
                    text = date,
                    textColor = GPColor.TextLightGray,
                    fontFamily = GPFontFamily.Bold,
                    textSize = 12.gsp
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(12.gdp),
                    painter = painterResource(Res.drawable.icon_chat),
                    colorFilter = ColorFilter.tint(GPColor.TextLightGray),
                    contentDescription = null
                )
                SpW(4.gdp)
                GPText(
                    text = commentCount.toString(),
                    textColor = GPColor.TextLightGray,
                    fontFamily = GPFontFamily.Bold,
                    textSize = 12.gsp
                )
            }
        }
    }
}
