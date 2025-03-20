package com.project.giunne.common.presentation.community.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.data.remote.response.PostingDetailListResponse
import com.project.giunne.common.data.remote.response.PostingDetailResponse
import com.project.giunne.common.presentation.common.charactor.GPSmallCharacter
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
    postingDetailInfo: PostingDetailResponse = PostingDetailResponse()
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        GPSquircleShape(
            modifier = Modifier.size(56.gdp),
            backgroundColor = GPColor.BackgroundLightGray,
            content = {
                GPSmallCharacter(
                    modifier = Modifier.fillMaxSize(),
                    wearingItems = postingDetailInfo.playerInfo.wearingItems
                )
            }
        )
        SpW(8.gdp)
        GPText(
            modifier = Modifier.weight(1f),
            text = postingDetailInfo.playerInfo.nickname,
            textColor = GPColor.TextBlack_232323,
            textSize = 14.gsp,
            fontFamily = GPFontFamily.Regular
        )
        GPText(
            text = postingDetailInfo.updateTime.substringBefore('T'),
            textColor = GPColor.TextLightGray,
            fontFamily = GPFontFamily.Bold,
            textSize = 12.gsp
        )
    }
}