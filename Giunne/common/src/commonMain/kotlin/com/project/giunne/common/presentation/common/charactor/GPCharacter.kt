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
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.window.Popup
import coil3.compose.AsyncImage
import com.project.giunne.common.data.remote.response.Item
import com.project.giunne.common.data.util.DefineUrl.IMAGE_BASE_URL
import com.project.giunne.common.util.gdp

@Composable
fun GPCharacter(
    modifier: Modifier = Modifier,
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
            var width by remember { mutableStateOf(0) }
            var height by remember { mutableStateOf(0) }
            // 이미지 크기만 측정 -> 추후 서버에 이미지 크기도 함께 저장하도록 수정
            Popup {
                AsyncImage(
                    modifier = Modifier
                        .onSizeChanged { size ->
                            width = size.width
                            height = size.height
                        }
                        .alpha(0f),
                    model = IMAGE_BASE_URL + item.itemImages.first().fileUrl,
                    contentDescription = null
                )
            }

            if (item.categoryId !in setOf(1, 6)) {
                if (item.itemImages.first().itemImagePositions.isNotEmpty()) {
                    AsyncImage(
                        modifier = Modifier
                            .size(
                                width = (width / 4).gdp,
                                height = (height / 4).gdp
                            )
                            .offset(
                                /* TODO(캐릭터 Level에 따라 입을 수 있는 아이템들 보여줌) */
                                x = item.itemImages.first().itemImagePositions.find { it.level == 6 }?.positionX?.gdp ?: 0.gdp,
                                y = item.itemImages.first().itemImagePositions.find { it.level == 6 }?.positionY?.gdp ?: 0.gdp
                            ),
                        model = IMAGE_BASE_URL + item.itemImages.first().fileUrl,
                        contentDescription = null
                    )
                }
            }
        }
    }
}