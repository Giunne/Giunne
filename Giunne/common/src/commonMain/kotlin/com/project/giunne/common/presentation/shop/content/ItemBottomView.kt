package com.project.giunne.common.presentation.shop.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    types: List<CategoryTypeResponse>,
    shopStore: ShopStore,
    state: CharacterState,
    onItemClick: (Item) -> Unit,
    onTypeSelected: (Long) -> Unit,
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
                selectedType = state.selectedType,
                onTypeSelected = { id ->
                    onTypeSelected(id)
                }
            )
            ItemGridList(
                modifier = Modifier.weight(1f),
                items = state.categoryItem,
                selectedItems = state.selectedItems,
                purchasedItems = state.purchasedItems,
                onItemClick = { item ->
                    onItemClick(item)
                }
            )
            if (state.wearingItems != state.selectedItems) {
                ItemModifyBottomView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    onUndoClick = {
                        shopStore.onUndo()
                    },
                    onModifyClick = {
                        shopStore.onModifyWearingItems()
                    }
                )
            }
        }
    }
}