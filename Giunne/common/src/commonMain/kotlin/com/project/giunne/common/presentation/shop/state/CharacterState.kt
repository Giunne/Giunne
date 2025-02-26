package com.project.giunne.common.presentation.shop.state

import com.project.giunne.common.data.remote.response.CategoryTypeResponse
import com.project.giunne.common.data.remote.response.Item
import com.project.giunne.common.data.remote.response.PaginationInfo
import com.project.giunne.common.data.util.DataThrowable
import com.project.giunne.common.data.util.DefineUrl.IMAGE_BASE_URL

data class CharacterState(
    val character: String = IMAGE_BASE_URL + "web/shop/avatar/고양이 6단계.PNG",
    val currentLevel: Int = 0,
    val selectedCharacter: String = character,
    val selectedType: Long = 2,
    val categoryMap: Map<Long, List<CategoryTypeResponse>> = mapOf(),
    val categoryItem: List<Item> = listOf(),
    val paginationInfo: PaginationInfo = PaginationInfo(),
    val wearingItems: List<Item> = listOf(),
    val selectedItems: List<Item> = wearingItems,
    val error: DataThrowable? = null
)

sealed interface ShopEvent
