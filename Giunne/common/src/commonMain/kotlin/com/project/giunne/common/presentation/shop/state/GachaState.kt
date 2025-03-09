package com.project.giunne.common.presentation.shop.state

import com.project.giunne.common.data.remote.response.GachaResponse
import com.project.giunne.common.data.remote.response.Item
import com.project.giunne.common.data.util.DataThrowable
import org.jetbrains.compose.resources.DrawableResource

data class GachaState(
    val remainPoint: Int = 0,
//    val gachaInfo: List<GachaResponse> = listOf(),
    val generalGachaInfo: GachaResponse = GachaResponse(),
    val premiumGachaInfo: GachaResponse = GachaResponse(),
    val randomItem: Item = Item(),
    val loading: Boolean = false,
    val error: DataThrowable? = null
)

sealed interface GachaEvent