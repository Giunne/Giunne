package com.project.giunne.common.presentation.shop.state

data class GachaState(
    val remainPoint: Int = 0,
    val gachaCost: Int = 0,
    val randomItem: RandomItem = RandomItem()
)

sealed interface GachaEvent