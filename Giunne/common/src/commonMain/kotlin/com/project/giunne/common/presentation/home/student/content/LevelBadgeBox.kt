package com.project.giunne.common.presentation.home.student.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun LevelBadgeBox(
    modifier: Modifier,
    level: Int,
) {
    Row(
        modifier = modifier
            .background(
                color = GPColor.MainOrangeColor,
                shape = RoundedCornerShape(8.gdp)
            )
            .padding(horizontal = 12.gdp, vertical = 6.gdp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.gdp)
    ) {
        GPText(
            text = "레벨",
            textSize = 12.gsp,
            textColor = GPColor.White,
        )
        GPText(
            text = "$level",
            textSize = 12.gsp,
            textColor = GPColor.White
        )
    }
}