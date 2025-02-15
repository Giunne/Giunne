package com.project.giunne.common.presentation.shop.state

import org.jetbrains.compose.resources.DrawableResource

data class GachaState(
    val remainPoint: Int = 240,
    val gachaCost: Int = 100,
    /*TODO(Drawable -> API 나오면 변경)*/
    val gachaItemList: List<DrawableResource> = listOf(),
    val randomItem: RandomItem = RandomItem()
)

sealed interface GachaEvent