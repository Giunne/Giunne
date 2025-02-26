package com.project.giunne.common.presentation.shop.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.project.giunne.common.data.remote.response.Item
import com.project.giunne.common.util.gdp

@Composable
fun ItemGridList(
    modifier: Modifier = Modifier,
    lazyGridState: LazyGridState,
    currentLevel: Int,
    items: List<Item>,
    selectedItems: List<Item>,
    onItemClick: (Item) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        state = lazyGridState,
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(start = 16.gdp, end = 16.gdp, bottom = 16.gdp),
        verticalArrangement = Arrangement.spacedBy(8.gdp),
        horizontalArrangement = Arrangement.spacedBy(8.gdp)
    ) {
        items.forEach { item ->
            item {
                ItemBox(
                    currentLevel = currentLevel,
                    item = item,
                    selectedItems = selectedItems,
                    onItemClick = { item ->
                        onItemClick(item)
                    }
                )
            }
        }
    }
}