package com.project.giunne.common.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class CategoryItemResponse(
    val data: List<Item> = listOf(),
    val paginationInfo: PaginationInfo = PaginationInfo()
)

@Serializable
data class Item(
    val id: Long,
    val categoryId: Int,
    val itemDescription: String,
    val itemGrade: String,
    val thumbnailUrl: String?,
    val itemImages: List<ItemCategoryImage>,
    val itemName: String,
    val needLevel: Int,
    val price: Int,
    val sortSeq: Int
)

@Serializable
data class ItemCategoryImage(
    val fileUrl: String,
    val id: Int,
    val level: Int,
    val isRepresent: Boolean,
    val itemImagePositions: List<ItemImagePosition>
)

@Serializable
data class ItemImagePosition(
    val id: Int,
    val level: Int,
    val positionX: Float,
    val positionY: Float,
    val positionZ: Float
)