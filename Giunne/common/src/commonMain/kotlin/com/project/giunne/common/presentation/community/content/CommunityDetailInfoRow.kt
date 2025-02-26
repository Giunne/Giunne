package com.project.giunne.common.presentation.community.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.presentation.common.shape.GPSquircleShape
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.community.student.dummy.CommunityDto
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import org.jetbrains.compose.resources.painterResource

@Composable
fun CommunityDetailInfoRow(
    modifier: Modifier = Modifier,
    communityDto: CommunityDto
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        GPSquircleShape(
            modifier = Modifier
                .size(56.gdp),
            backgroundColor = GPColor.BackgroundGray_F6F6F6
        ) {
            Image(
                modifier = Modifier.size(48.gdp),
                painter = painterResource(communityDto.character),
                contentDescription = null
            )
        }
        SpW(8.gdp)
        GPText(
            modifier = Modifier.weight(1f),
            text = communityDto.name,
            textColor = GPColor.TextBlack_232323,
            textSize = 14.gsp,
            fontFamily = GPFontFamily.Regular
        )
        GPText(
            text = communityDto.date,
            textColor = GPColor.TextLightGray,
            fontFamily = GPFontFamily.Bold,
            textSize = 12.gsp
        )
    }
}