package com.project.giunne.common.presentation.shop.content

import androidx.compose.ui.graphics.Brush
import com.project.giunne.common.ui.theme.GPColor

val sRankColorBrush = Brush.linearGradient(
    colors = listOf(
        GPColor.SRankItemColor1,
        GPColor.SRankItemColor2,
        GPColor.SRankItemColor3,
        GPColor.SRankItemColor4,
    )
)

val aRankColorBrush = Brush.linearGradient(
    colors = listOf(
        GPColor.ARankItemColor1,
        GPColor.ARankItemColor2
    )
)

val bRankColorBrush = Brush.linearGradient(
    colors = listOf(
        GPColor.MainOrangeColor,
        GPColor.MainOrangeColor
    )
)

val cRankColorBrush = Brush.linearGradient(
    colors = listOf(GPColor.ButtonLightGray,
        GPColor.ButtonLightGray
    )
)