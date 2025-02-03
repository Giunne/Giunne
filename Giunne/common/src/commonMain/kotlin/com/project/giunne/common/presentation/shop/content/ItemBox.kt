package com.project.giunne.common.presentation.shop.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.shop.dummy.TestItem
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import org.jetbrains.compose.resources.painterResource

@Composable
fun ItemBox(
    modifier: Modifier = Modifier,
    item: TestItem,
    purchasedItems: List<TestItem>,
    selectedItems: List<TestItem>,
    onItemClick: (TestItem) -> Unit,
) {
    BoxWithConstraints (
        modifier = modifier
            .aspectRatio(1f)
            .background(
                color = GPColor.BackgroundGray_F6F6F6,
                shape = RoundedCornerShape(16.gdp)
            )
            .border(
                width = if (selectedItems.contains(item)) 2.gdp else 0.gdp,
                shape = RoundedCornerShape(16.gdp),
                color = if (selectedItems.contains(item)) GPColor.MainOrangeColor else GPColor.Transparent
            )
            .noRippleClickable { onItemClick(item) },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .width(maxWidth / 2)
                .wrapContentHeight(),
            painter = painterResource(item.image),
            contentDescription = "아이템"
        )
    }
}