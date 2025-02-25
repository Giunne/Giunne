package com.project.giunne.common.presentation.shop.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun ItemModifyBottomView(
    modifier: Modifier,
    onUndoClick: () -> Unit,
    onModifyClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .background(
                color = GPColor.White,
                shape = RoundedCornerShape(topStart = 16.gdp, topEnd = 16.gdp)
            )
            .padding(top = 8.gdp, bottom = 16.gdp, start = 16.gdp, end = 16.gdp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.gdp)
    ) {
        GPButton(
            modifier = Modifier
                .wrapContentWidth()
                .height(56.gdp),
            normalColor = GPColor.ButtonLightGray,
            pressColor = GPColor.ButtonPressLightGray,
            hoverColor = GPColor.ButtonHoverLightGray,
            onClick = onUndoClick,
        ) {
            GPText(
                text = "되돌리기",
                textSize = 14.gsp,
                fontFamily = GPFontFamily.Bold,
                textColor = GPColor.TextBlack
            )
        }
        GPButton(
            modifier = Modifier
                .weight(1f)
                .height(56.gdp),
            normalColor = GPColor.ButtonOrange,
            pressColor = GPColor.ButtonPressOrange,
            hoverColor = GPColor.ButtonHoverOrange,
            onClick = onModifyClick,
        ) {
            GPText(
                text = "저장하기",
                textSize = 14.gsp,
                fontFamily = GPFontFamily.Bold,
                textColor = GPColor.White
            )
        }
    }
}