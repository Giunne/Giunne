package com.project.giunne.common.presentation.shop.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.shop.state.Item
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import org.jetbrains.compose.resources.painterResource

@Composable
fun ItemBox(
    modifier: Modifier = Modifier,
    item: Item,
    purchasedItems: List<Item>,
    selectedItems: List<Item>,
    onItemClick: (Item) -> Unit,
) {
    BoxWithConstraints (
        modifier = modifier
            .aspectRatio(1f)
            .background(
                color = GPColor.BackgroundGray_F6F6F6,
                shape = RoundedCornerShape(16.gdp)
            )
            .border(
                width = 3.gdp,
                shape = RoundedCornerShape(16.gdp),
                brush = when (item.rank) {
                    "S" -> sRankColorBrush
                    "A" -> aRankColorBrush
                    "B" -> bRankColorBrush
                    else -> cRankColorBrush
                },
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

        if (selectedItems.contains(item)) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = GPColor.BackgroundBlack.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(16.gdp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                GPText(
                    text = "착용중",
                    textColor = GPColor.White,
                    fontFamily = GPFontFamily.ExtraBold,
                    textSize = 14.gsp
                )
            }
        }
    }
}