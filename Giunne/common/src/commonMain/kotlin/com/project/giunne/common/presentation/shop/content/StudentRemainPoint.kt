package com.project.giunne.common.presentation.shop.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun StudentRemainPoint(
    modifier: Modifier,
    remainPoint: Int,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.gdp)
    ) {
        Box(
            modifier = modifier
                .background(shape = CircleShape, color = GPColor.MainOrangeColor),
            contentAlignment = Alignment.Center
        ) {
            GPText(
                fontFamily = GPFontFamily.Bold,
                textColor = GPColor.White,
                text = "C",
                textSize = 12.gsp,
            )
        }
        GPText(
            fontFamily = GPFontFamily.Bold,
            text = remainPoint.toString(),
            textColor = GPColor.MainOrangeColor,
            textSize = 14.gsp,
        )
    }
}