package com.project.giunne.common.presentation.shop.dummy

import androidx.compose.ui.unit.IntSize
import com.project.giunne.Res
import com.project.giunne.common.presentation.shop.state.ItemType
import com.project.giunne.test_item_body
import com.project.giunne.test_item_body1
import com.project.giunne.test_item_body2
import com.project.giunne.test_item_character
import com.project.giunne.test_item_chracter_gacha
import com.project.giunne.test_item_face
import com.project.giunne.test_item_face2
import com.project.giunne.test_item_head
import com.project.giunne.test_item_head2
import org.jetbrains.compose.resources.DrawableResource

/* TODO("나중에 API 나오구 Spec에 맞게 변경") */
data class TestItem(
    val type: ItemType,
    val size: IntSize,
    val offsetX: Int,
    val offsetY: Int,
    val image: DrawableResource,
)

val headItemWithOffset1 = TestItem(ItemType.HEAD, size = IntSize(166, 157), offsetX = 45, offsetY = 12, image = Res.drawable.test_item_head)
val headItemWithOffset2 = TestItem(ItemType.HEAD, size = IntSize(56, 40), offsetX = 100, offsetY = 26, image = Res.drawable.test_item_head2)
val bodyItemWithOffset1 = TestItem(ItemType.BODY, size = IntSize(108, 73), offsetX = 74, offsetY = 138, image = Res.drawable.test_item_body)
val bodyItemWithOffset2 = TestItem(ItemType.BODY, size = IntSize(164, 200), offsetX = 46, offsetY = 17, image = Res.drawable.test_item_body1)
val bodyItemWithOffset3 = TestItem(ItemType.BODY, size = IntSize(120, 62), offsetX = 68, offsetY = 128, image = Res.drawable.test_item_body2)
val faceItemWithOffset1 = TestItem(ItemType.FACE, size = IntSize(33, 39), offsetX = 125, offsetY = 83, image = Res.drawable.test_item_face)
val faceItemWithOffset2 = TestItem(ItemType.FACE, size = IntSize(59, 27), offsetX = 99, offsetY = 82, image = Res.drawable.test_item_face2)
val characterItemWithOffsetGacha = TestItem(ItemType.CHARACTER, size = IntSize(33, 39), offsetX = 125, offsetY = 83, image = Res.drawable.test_item_chracter_gacha)
val characterItemWithOffset = Res.drawable.test_item_character

val gachaItems = listOf(
    Res.drawable.test_item_head,
    Res.drawable.test_item_head2,
    Res.drawable.test_item_body,
    Res.drawable.test_item_body1,
    Res.drawable.test_item_body2,
    Res.drawable.test_item_face,
    Res.drawable.test_item_face2,
)
