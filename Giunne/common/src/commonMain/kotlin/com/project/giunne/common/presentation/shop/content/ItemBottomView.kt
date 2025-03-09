package com.project.giunne.common.presentation.shop.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.project.giunne.common.data.remote.response.CategoryTypeResponse
import com.project.giunne.common.data.remote.response.Item
import com.project.giunne.common.presentation.shop.intent.ShopStore
import com.project.giunne.common.presentation.shop.state.CharacterState
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp

@Composable
fun ItemBottomView(
    modifier: Modifier,
    lazyGridState: LazyGridState,
    types: List<CategoryTypeResponse>,
    selectedType: Long,
    onTypeSelected: (Long) -> Unit,
    categoryItem: List<Item>,
    onItemClick: (Item) -> Unit,
    currentLevel: Int,
    wearingItems: List<Item>,
    selectedItems: List<Item>,
    onUndoButtonClicked: () -> Unit,
    saveEquipmentState: (List<Item>)  -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = GPColor.White,
                    shape = RoundedCornerShape(topStart = 16.gdp, topEnd = 16.gdp)
                )
        ) {
            Spacer(modifier = Modifier.height(16.gdp))

            ItemChipGroup(
                types = types,
                selectedType = selectedType,
                onTypeSelected = { id ->
                    onTypeSelected(id)
                }
            )
            if (categoryItem.isEmpty()) {
                EmptyItemList(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            } else {
                ItemGridList(
                    modifier = Modifier.weight(1f),
                    currentLevel = currentLevel,
                    lazyGridState = lazyGridState,
                    items = categoryItem,
                    selectedItems = selectedItems,
                    onItemClick = { item ->
                        onItemClick(item)
                    }
                )
            }
            if (wearingItems.map { it.id } != selectedItems.map { it.id }) {
                ItemModifyBottomView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    onUndoClick = {
                        onUndoButtonClicked()
                    },
                    onModifyClick = {
//                        shopStore.onModifyWearingItems()
                        saveEquipmentState(selectedItems)
                    }
                )
            }
        }
    }
}