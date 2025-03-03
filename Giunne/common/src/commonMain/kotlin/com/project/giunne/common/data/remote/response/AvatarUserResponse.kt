package com.project.giunne.common.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class AvatarUserResponse(
    val characterNo: Int,
    val exp: Int,
    val id: Int,
    val level: Int,
    val nickname: String,
    val point: Int,
    val recreationCode: String,
    val recreationId: Int,
    val recreationName: String,
    val teacherId: Int,
    val teacherLoginId: String,
    val teacherName: String? = null,
    val wearingItemIds: List<Int>,
    val wearingItems: List<WearingItem>
)

@Serializable
data class WearingItem(
    val categoryId: Int,
    val id: Int,
    val itemDescription: String,
    val itemGrade: String,
    val itemImage: ItemImage,
    val itemName: String,
    val needLevel: Int,
    val price: Int,
    val sortSeq: Int
)

@Serializable
data class ItemImage(
    val fileUrl: String,
    val id: Int,
    val isRepresent: Boolean,
    val level: Int
)