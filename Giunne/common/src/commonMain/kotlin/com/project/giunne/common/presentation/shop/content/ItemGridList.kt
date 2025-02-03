package com.project.giunne.common.presentation.shop.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import com.project.giunne.common.presentation.shop.dummy.TestItem
import com.project.giunne.common.util.gdp

@Composable
fun ItemGridList(
    items: List<TestItem>,
    purchasedItems: List<TestItem>,
    selectedItems: List<TestItem>,
    onItemClick: (TestItem) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(start = 16.gdp, end = 16.gdp, bottom = 16.gdp),
        verticalArrangement = Arrangement.spacedBy(8.gdp),
        horizontalArrangement = Arrangement.spacedBy(8.gdp)
    ) {
        items.forEach { item ->
            item {
                ItemBox(
                    item = item,
                    purchasedItems = purchasedItems,
                    selectedItems = selectedItems,
                    onItemClick = { item ->
                        onItemClick(item)
                    }
                )
            }
        }
    }
}