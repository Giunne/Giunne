package com.project.giunne.common.presentation.home.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp

@Composable
fun RowWithDropShadow(
    modifier: Modifier,
    content: @Composable RowScope.() -> Unit = {}
) {
    Row(
        modifier = modifier
            .shadow(
                shape = RoundedCornerShape(16.gdp),
                ambientColor = GPColor.TextBlack.copy(alpha = 0.1f),
                elevation = 4.gdp
            )
            .clip(RoundedCornerShape(16.gdp))
            .background(Color.White)
            .padding(16.gdp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        content()
    }
}