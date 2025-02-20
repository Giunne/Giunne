package com.project.giunne.common.presentation.shop.state

import com.project.giunne.common.data.remote.response.GachaResponse
import org.jetbrains.compose.resources.DrawableResource

data class GachaState(
    val remainPoint: Int = 0,
    val gachaInfo: List<GachaResponse> = listOf(),
    /*TODO(Drawable -> API 나오면 변경)*/
    val gachaItemList: List<DrawableResource> = listOf(),
    val randomItem: RandomItem = RandomItem()
)

sealed interface GachaEvent