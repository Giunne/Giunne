package com.project.giunne.common.presentation.shop.content

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.shop.state.ItemType
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun ItemChipGroup(
    types: List<ItemType>,
    selectedType: ItemType,
    onTypeSelected: (ItemType) -> Unit,
) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(8.gdp)
    ) {
        types.forEach { type ->
            val isSelected = type == selectedType
            ItemChip(
                type = type,
                isSelected = isSelected,
                onSelected = { itemType ->
                    onTypeSelected(itemType)
                }
            )
        }
    }
}

@Composable
fun ItemChip(
    type: ItemType,
    isSelected: Boolean,
    onSelected: (ItemType) -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 8.gdp)
            .clip(RoundedCornerShape(16.gdp))
            .background(
                color = if (isSelected) GPColor.BackgroundGray_EBEBEB else GPColor.Transparent
            )
            .noRippleClickable {
                onSelected(type)
            }
            .padding(vertical = 4.gdp, horizontal = 12.gdp),
        contentAlignment = Alignment.Center
    ) {
        GPText(
            text = type.title,
            textSize = 12.gsp
        )
    }
}