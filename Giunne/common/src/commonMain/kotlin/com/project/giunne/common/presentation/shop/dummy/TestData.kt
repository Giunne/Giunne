package com.project.giunne.common.presentation.shop.dummy

import androidx.compose.ui.unit.IntSize
import com.project.giunne.Res
import com.project.giunne.common.presentation.shop.state.Item
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

val headItemWithOffset1 = Item(rank = "S", ItemType.HEAD, size = IntSize(166, 157), offsetX = 45, offsetY = 12, image = Res.drawable.test_item_head)
val headItemWithOffset2 = Item(rank = "A", ItemType.HEAD, size = IntSize(56, 40), offsetX = 100, offsetY = 26, image = Res.drawable.test_item_head2)
val bodyItemWithOffset1 = Item(rank = "A", ItemType.BODY, size = IntSize(108, 73), offsetX = 74, offsetY = 138, image = Res.drawable.test_item_body)
val bodyItemWithOffset2 = Item(rank = "S", ItemType.BODY, size = IntSize(164, 200), offsetX = 46, offsetY = 17, image = Res.drawable.test_item_body1)
val bodyItemWithOffset3 = Item(rank = "B", ItemType.BODY, size = IntSize(120, 62), offsetX = 68, offsetY = 128, image = Res.drawable.test_item_body2)
val faceItemWithOffset1 = Item(rank = "S", ItemType.FACE, size = IntSize(33, 39), offsetX = 125, offsetY = 83, image = Res.drawable.test_item_face)
val faceItemWithOffset2 = Item(rank = "S", ItemType.FACE, size = IntSize(59, 27), offsetX = 99, offsetY = 82, image = Res.drawable.test_item_face2)
val characterItemWithOffsetGacha = Item(rank = "S", ItemType.CHARACTER, size = IntSize(33, 39), offsetX = 125, offsetY = 83, image = Res.drawable.test_item_chracter_gacha)
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
