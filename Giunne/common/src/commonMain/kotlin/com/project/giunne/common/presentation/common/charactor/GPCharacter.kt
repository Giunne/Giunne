package com.project.giunne.common.presentation.common.charactor

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import coil3.compose.AsyncImage
import com.project.giunne.common.data.remote.response.Item
import com.project.giunne.common.data.util.DefineUrl.IMAGE_BASE_URL
import com.project.giunne.common.util.gdp

@Composable
fun GPCharacter(
    modifier: Modifier = Modifier,
    currentLevel: Int,
    character: String,
    items: List<Item> = listOf()
) {
    Box {
        AsyncImage(
            modifier = modifier,
            model = character,
            contentDescription = null
        )
        items.forEach { item ->
            // 이미지 크기만 측정 -> 추후 서버에 이미지 크기도 함께 저장하도록 수정
            var itemSize by remember { mutableStateOf(IntSize.Zero) }

            SubcomposeLayout { constraints ->

                // 아이템이 2개 인건 레벨별로 이미지가 다름 -> 매핑
                val currentItem = if (1 < item.itemImages.size) {
                    item.itemImages.find { it.level == currentLevel }
                } else {
                    item.itemImages.first()
                }
                val unconstrainedConstraints = constraints.copy(
                    minWidth = 0,
                    minHeight = 0,
                    maxWidth = Int.MAX_VALUE,
                    maxHeight = Int.MAX_VALUE
                )

                subcompose("measure") {
                    AsyncImage(
                        modifier = Modifier.onSizeChanged {
                            itemSize = it
                        }
                            .alpha(0.1f),
                        model = IMAGE_BASE_URL + currentItem?.fileUrl,
                        contentDescription = null
                    )
                }.first().measure(unconstrainedConstraints)

                val contentMeasure = subcompose("content") {
                    AsyncImage(
                        modifier = Modifier
                            .size(
                                width = (itemSize.width / 4).gdp,
                                height = (itemSize.height / 4).gdp
                            )
                            .offset(
                                x = currentItem?.itemImagePositions?.find { it.level == currentLevel }?.positionX?.gdp ?: 0.gdp,
                                y = currentItem?.itemImagePositions?.find { it.level == currentLevel }?.positionY?.gdp ?: 0.gdp
                            ),
                        model = IMAGE_BASE_URL + currentItem?.fileUrl,
                        contentDescription = null
                    )
                }.first().measure(constraints)


                if (item.categoryId != 6) {
                    layout(contentMeasure.width, contentMeasure.height) {
                        contentMeasure.place(0, 0)
                    }
                } else {
                    layout(0, 0) {}
                }
            }
        }
    }
}