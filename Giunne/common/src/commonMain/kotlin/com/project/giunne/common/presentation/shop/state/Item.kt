package com.project.giunne.common.presentation.shop.state

import androidx.compose.ui.unit.IntSize
import org.jetbrains.compose.resources.DrawableResource

data class Item(
    val rank: String = "C",
    val type: ItemType,
    val size: IntSize,
    val offsetX: Int,
    val offsetY: Int,
    /* TODO(나중에 image url로 변경)*/
    val image: DrawableResource,
    val imageUrl: String = "",
)