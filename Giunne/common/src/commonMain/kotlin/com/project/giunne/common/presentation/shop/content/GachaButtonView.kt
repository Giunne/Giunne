package com.project.giunne.common.presentation.shop.content

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun GachaButtonView(
    modifier: Modifier,
    gachaCost: Int,
    remainPoint: Int,
    onGachaClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.gdp)
    ) {
        Box(
            modifier = Modifier
                .height(48.gdp)
                .wrapContentWidth()
                .border(width = 2.gdp, shape = RoundedCornerShape(12.gdp), color = GPColor.MainOrangeColor)
                .padding(horizontal = 18.gdp),
            contentAlignment = Alignment.Center
        ) {
            StudentRemainPoint(
                modifier = Modifier.size(24.gdp),
                remainPoint = remainPoint
            )
        }

        GPButton(
            modifier = Modifier
                .weight(1f)
                .height(48.gdp),
            normalColor = GPColor.ButtonOrange,
            pressColor = GPColor.ButtonPressOrange,
            hoverColor = GPColor.ButtonHoverOrange,
            onClick = { onGachaClick() },
        ) {
            GPText(
                text = "${gachaCost}포인트로 뽑기",
                textSize = 14.gsp,
                fontFamily = GPFontFamily.Bold,
                textColor = GPColor.White
            )
        }
    }
}