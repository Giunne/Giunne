package com.project.giunne.common.presentation.shop.state

import com.project.giunne.Res
import com.project.giunne.common.presentation.shop.dummy.headItemWithOffset1
import com.project.giunne.common.presentation.shop.dummy.headItemWithOffset2
import com.project.giunne.test_character
import org.jetbrains.compose.resources.DrawableResource

data class CharacterState(
    val character: DrawableResource = Res.drawable.test_character,
    val selectedCharacter: DrawableResource = character,
    val selectedType: ItemType = ItemType.HEAD,
    val selectedTypeItems: List<Item> = listOf(headItemWithOffset1, headItemWithOffset2, headItemWithOffset2),
    val wearingItems: List<Item> = listOf(headItemWithOffset1),
    val selectedItems: List<Item> = wearingItems,
    val purchasedItems: List<Item> = listOf()
)

sealed interface ShopEvent
