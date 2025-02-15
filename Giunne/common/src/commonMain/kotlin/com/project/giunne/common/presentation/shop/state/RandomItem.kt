package com.project.giunne.common.presentation.shop.state

import com.project.giunne.common.presentation.shop.dummy.gachaItems1
import org.jetbrains.compose.resources.DrawableResource

data class RandomItem(
    val rank: String = "S",
    val name: String = "노란색 우비",
    /*TODO(테스트용 Drawable)*/
    val drawable: DrawableResource = gachaItems1[3],
    val imageUrl: String = "",
)