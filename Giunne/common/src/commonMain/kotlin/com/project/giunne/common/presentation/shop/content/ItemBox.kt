package com.project.giunne.common.presentation.shop.content

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
import coil3.compose.AsyncImage
import com.project.giunne.common.data.remote.response.Item
import com.project.giunne.common.data.util.DefineUrl.IMAGE_BASE_URL
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.common.util.removeSpaceUrl

@Composable
fun ItemBox(
    modifier: Modifier = Modifier,
    currentLevel: Int,
    item: Item,
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
                brush = when (item.itemGrade) {
                    "S" -> sRankColorBrush
                    "A" -> aRankColorBrush
                    "B" -> bRankColorBrush
                    else -> cRankColorBrush
                },
            )
            .noRippleClickable { onItemClick(item) },
        contentAlignment = Alignment.Center
    ) {

        item.thumbnailUrl?.let { thumbnailUrl ->
            if (thumbnailUrl.isNotEmpty()) {
                AsyncImage(
                    modifier = Modifier
                        .width(maxWidth / 2)
                        .wrapContentHeight(),
                    model = IMAGE_BASE_URL + thumbnailUrl.removeSpaceUrl(),
                    contentDescription = "아이템"
                )
            }
        } ?: run {
            // 아이템이 2개 인건 레벨별로 이미지가 다름
            val imageUrl = if (1 < item.itemImages.size) {
                IMAGE_BASE_URL + item.itemImages.find { it.level == currentLevel }?.fileUrl
            } else {
                IMAGE_BASE_URL + item.itemImages.first().fileUrl
            }
            AsyncImage(
                modifier = Modifier
                    .width(maxWidth / 2)
                    .wrapContentHeight(),
                model = imageUrl.removeSpaceUrl(),
                contentDescription = "아이템"
            )
        }

        if (selectedItems.find { selectedItems -> selectedItems.id == item.id } != null) {
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