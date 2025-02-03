package com.project.giunne.common.presentation.shop.intent

import com.project.giunne.common.base.BaseComponent
import com.project.giunne.common.presentation.shop.dummy.TestItem
import com.project.giunne.common.presentation.shop.dummy.bodyItemWithOffset1
import com.project.giunne.common.presentation.shop.dummy.bodyItemWithOffset2
import com.project.giunne.common.presentation.shop.dummy.bodyItemWithOffset3
import com.project.giunne.common.presentation.shop.dummy.characterItemWithOffset
import com.project.giunne.common.presentation.shop.dummy.characterItemWithOffsetGacha
import com.project.giunne.common.presentation.shop.dummy.faceItemWithOffset1
import com.project.giunne.common.presentation.shop.dummy.faceItemWithOffset2
import com.project.giunne.common.presentation.shop.dummy.headItemWithOffset1
import com.project.giunne.common.presentation.shop.dummy.headItemWithOffset2
import com.project.giunne.common.presentation.shop.state.CharacterState
import com.project.giunne.common.presentation.shop.state.ItemType
import com.project.giunne.common.presentation.shop.state.ShopEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class ShopStore: BaseComponent<CharacterState, ShopEvent>(
    scope = CoroutineScope(Dispatchers.IO),
    initialState = CharacterState()
) {
    /* TODO(API에 따라 DTO 변경 필요) */
    fun onChangeItem(item: TestItem) {
        setState {
            copy(
                selectedCharacter = if (item.type == ItemType.CHARACTER) {
                    characterItemWithOffset
                } else this.selectedCharacter,
                selectedItems = if (selectedItems.contains(item)) {
                    selectedItems.toMutableList().apply {
                        remove(item)
                    }
                } else {
                    selectedItems.toMutableList().apply {
                        removeIf { it.type == item.type }
                        add(item)
                    }
                }
            )

        }
    }

    fun onChangeType(type: ItemType) {
        setState {
            when (type) {
                ItemType.HEAD -> copy(
                    selectedType = type,
                    selectedTypeItems = listOf(headItemWithOffset1, headItemWithOffset2)
                )
                ItemType.BODY -> copy(
                    selectedType = type,
                    selectedTypeItems = listOf(bodyItemWithOffset1, bodyItemWithOffset2, bodyItemWithOffset3)
                )
                ItemType.FACE -> copy(
                    selectedType = type,
                    selectedTypeItems = listOf(faceItemWithOffset1, faceItemWithOffset2)
                )
                ItemType.CHARACTER -> copy(
                    selectedType = type,
                    selectedTypeItems = listOf(characterItemWithOffsetGacha)
                )
            }
        }
    }

    fun onUndo() {
        setState {
            copy(
                selectedCharacter = character,
                selectedItems = wearingItems
            )
        }
    }

    fun onModifyWearingItems() {
        setState {
            copy(
                character = selectedCharacter,
                wearingItems = selectedItems
            )
        }
    }

    private fun getDuplicateTypeItemOrNull(type: ItemType): TestItem? {
        return uiState.value.selectedItems.find { it.type == type }
    }
}