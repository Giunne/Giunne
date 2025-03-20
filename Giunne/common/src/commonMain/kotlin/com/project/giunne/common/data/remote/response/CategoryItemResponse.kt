package com.project.giunne.common.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryItemResponse(
    val data: List<Item> = listOf(),
    val paginationInfo: PaginationInfo = PaginationInfo()
)

@Serializable
data class Item(
    @SerialName("id") val id: Long = 0,
    @SerialName("categoryId") val categoryId: Int = 0,
    @SerialName("itemDescription") val itemDescription: String = "",
    @SerialName("itemGrade") val itemGrade: String = "",
    @SerialName("thumbnailUrl") val thumbnailUrl: String? = null,
    @SerialName("itemImages") val itemImages: List<ItemCategoryImage> = listOf(),
    @SerialName("itemName") val itemName: String = "",
    @SerialName("needLevel") val needLevel: Int = 0,
    @SerialName("price") val price: Int = 0,
    @SerialName("sortSeq") val sortSeq: Int = 0
)

@Serializable
data class ItemCategoryImage(
    val fileUrl: String,
    val id: Int,
    val level: Int,
    val isRepresent: Boolean,
    val itemImagePositions: List<ItemImagePosition> = listOf()
)

@Serializable
data class ItemImagePosition(
    val id: Int,
    val level: Int,
    val positionX: Float,
    val positionY: Float,
    val positionZ: Float
)