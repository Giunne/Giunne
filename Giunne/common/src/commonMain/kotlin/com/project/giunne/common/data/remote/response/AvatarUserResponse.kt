package com.project.giunne.common.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class AvatarUserResponse(
    val characterNo: Int = 0,
    val exp: Int = 0,
    val needExp: Int = 10,
    val id: Int = 0,
    val level: Int = 0,
    val nickname: String = "",
    val point: Int = 0,
    val recreationCode: String = "",
    val recreationId: Int = 0,
    val recreationName: String = "",
    val teacherId: Int = 0,
    val teacherLoginId: String = "",
    val teacherName: String? = null,
    val wearingItemIds: List<Int> = listOf(),
    val wearingItems: List<WearingItem> = listOf(),

    val myPoint: Int = 0
)

@Serializable
data class WearingItem(
    val id: Int,
    val categoryId: Int,
    val itemDescription: String,
    val itemGrade: String,
    val itemImage: ItemImage,
    val itemName: String,
    val needLevel: Int,
    val price: Int,
    val sortSeq: Int
) {
    fun asShopItem(): Item {
        return Item(
            id = id.toLong(),
            categoryId = categoryId,
            itemDescription = itemDescription,
            itemGrade = itemGrade,
            itemName = itemName,
            needLevel = needLevel,
            price = price,
            sortSeq = sortSeq,
            thumbnailUrl = "",
            itemImages = listOf(itemImage.asShopItemImage())
        )
    }
}

@Serializable
data class ItemImage(
    val fileUrl: String,
    val id: Int,
    val level: Int,
    val isRepresent: Boolean,
    val itemImagePosition: ItemPosition? = null
) {
    fun asShopItemImage(): ItemCategoryImage {
        return ItemCategoryImage(
            fileUrl = fileUrl,
            id = id,
            level = level,
            isRepresent = isRepresent,
            itemImagePositions = itemImagePosition?.let { position ->
                listOf(position.asShopItemImagePosition())
            } ?: listOf()
        )
    }
}

@Serializable
data class ItemPosition(
    val id: Int,
    val level: Int,
    val positionX: Float,
    val positionY: Float,
    val positionZ: Float
) {
    fun asShopItemImagePosition(): ItemImagePosition {
        return ItemImagePosition(
            id = id,
            level = level,
            positionX = positionX,
            positionY = positionY,
            positionZ = positionZ
        )
    }
}