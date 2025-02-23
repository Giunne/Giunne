package com.project.giunne.common.presentation.common.scrollbar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp


@Composable
actual fun VerticalScrollbar(
    modifier: Modifier,
    state: LazyListState
) {
    Box(
        modifier.verticalListScrollbar(state)
    )
}

@Composable
actual fun VerticalScrollbar(
    modifier: Modifier,
    state: LazyGridState
) {
    Box(
        modifier.verticalListScrollbar(state)
    )
}

@Composable
actual fun VerticalScrollbar(
    modifier: Modifier,
    state: ScrollState
) {
    Box(
        modifier.verticalScrollbar(state)
    )
}

@Composable
actual fun HorizontalScrollbar(
    modifier: Modifier,
    state: LazyListState
) {
    Box(
        modifier.horizontalListScrollbar(state)
    )
}

@Composable
fun Modifier.verticalListScrollbar(
    state: LazyListState,
    scrollbarWidth: Dp = 10.gdp,
    color: Color = GPColor.TextLightGray
): Modifier {
    val alpha by animateFloatAsState(targetValue = if(state.isScrollInProgress) 1f else 0.2f,
        animationSpec = tween(150, delayMillis = if(state.isScrollInProgress) 0 else 300),
        label = ""
    )

    return this then Modifier.drawWithContent {
        drawContent()

        val firstVisibleItemIndex = with(state.layoutInfo.visibleItemsInfo) {
            when (size) {
                0 -> null
                1 -> 0
                else -> {
                    val first = this[0]
                    val second = this[1]
                    // If either the indices or the offsets aren't continuous, then the first item is
                    // sticky, so we return 1
                    if ((first.index < second.index - 1) ||
                        (first.offset + first.size > second.offset))
                        1
                    else
                        0
                }
            }
        }

        if (firstVisibleItemIndex != null) {
            val firstVisibleItem = state.layoutInfo.visibleItemsInfo[firstVisibleItemIndex]

            val viewHeight = this.size.height
            val itemHeight = firstVisibleItem.size
            if (state.layoutInfo.visibleItemsInfo.size * itemHeight > viewHeight) {
                val contentHeight = (state.layoutInfo.totalItemsCount * itemHeight)

                val scrollbarHeight = (viewHeight * (viewHeight / contentHeight )).coerceIn(10.gdp.toPx() .. viewHeight)
                val stepYOffsetSize = 1 / state.layoutInfo.totalItemsCount.toFloat() * viewHeight
                val itemOffset = stepYOffsetSize * ((-firstVisibleItem.offset.toFloat()) / itemHeight)
                val scrollbarYOffset = ((firstVisibleItem.index.toFloat() / state.layoutInfo.totalItemsCount.toFloat()) * viewHeight) + itemOffset

                drawRoundRect(
                    cornerRadius = CornerRadius(scrollbarWidth.toPx() / 2, scrollbarWidth.toPx() / 2),
                    color = color,
                    topLeft = Offset(this.size.width - scrollbarWidth.toPx(), scrollbarYOffset),
                    size = Size(scrollbarWidth.toPx(), scrollbarHeight),
                    alpha = alpha
                )
            }
        }
    }
}

@Composable
fun Modifier.verticalListScrollbar(
    state: LazyGridState,
    scrollbarWidth: Dp = 10.gdp,
    color: Color = GPColor.TextLightGray
): Modifier {
    val alpha by animateFloatAsState(targetValue = if(state.isScrollInProgress) 1f else 0.2f,
        animationSpec = tween(150, delayMillis = if(state.isScrollInProgress) 0 else 300),
        label = ""
    )

    return this then Modifier.drawWithContent {
        drawContent()

        val firstVisibleItemIndex = with(state.layoutInfo.visibleItemsInfo) {
            when (size) {
                0 -> null
                1 -> 0
                2 -> 0
                else -> {
                    val first = this[0]
                    val second = this[2]
                    // If either the indices or the offsets aren't continuous, then the first item is
                    // sticky, so we return 1
                    if ((first.index < second.index - 2) ||
                        (first.offset.y + first.size.height > second.offset.y))
                        1
                    else
                        0
                }
            }
        }

        if (firstVisibleItemIndex != null) {
            val firstVisibleItem = state.layoutInfo.visibleItemsInfo[firstVisibleItemIndex]

            val viewHeight = this.size.height
            val itemHeight = firstVisibleItem.size.height.toFloat()
            val totalCount = state.layoutInfo.totalItemsCount.toFloat() / 2
            if (state.layoutInfo.visibleItemsInfo.size * itemHeight > viewHeight) {
                val contentHeight = (totalCount * itemHeight)

                val scrollbarHeight = (viewHeight * (viewHeight / contentHeight )).coerceIn(10.gdp.toPx() .. viewHeight)
                val stepYOffsetSize = 1 / totalCount * viewHeight
                val itemOffset = stepYOffsetSize * ((-firstVisibleItem.offset.y.toFloat()) / itemHeight)
                val scrollbarYOffset = (((firstVisibleItem.index.toFloat() / 2) / totalCount) * viewHeight) + itemOffset

                drawRoundRect(
                    cornerRadius = CornerRadius(scrollbarWidth.toPx() / 2, scrollbarWidth.toPx() / 2),
                    color = color,
                    topLeft = Offset(this.size.width - scrollbarWidth.toPx(), scrollbarYOffset),
                    size = Size(scrollbarWidth.toPx(), scrollbarHeight),
                    alpha = alpha
                )
            }
        }
    }
}

@Composable
fun Modifier.horizontalListScrollbar(
    state: LazyListState,
    scrollbarWidth: Dp = 10.gdp,
    color: Color = GPColor.TextLightGray
): Modifier {
    val alpha by animateFloatAsState(targetValue = if(state.isScrollInProgress) 1f else 0.2f,
        animationSpec = tween(150, delayMillis = if(state.isScrollInProgress) 0 else 300),
        label = ""
    )

    return this then Modifier.drawWithContent {
        drawContent()

        val firstVisibleItemIndex = with(state.layoutInfo.visibleItemsInfo) {
            when (size) {
                0 -> null
                1 -> 0
                else -> {
                    val first = this[0]
                    val second = this[1]
                    // If either the indices or the offsets aren't continuous, then the first item is
                    // sticky, so we return 1
                    if ((first.index < second.index - 1) ||
                        (first.offset + first.size > second.offset))
                        1
                    else
                        0
                }
            }
        }

        if (firstVisibleItemIndex != null) {
            val firstVisibleItem = state.layoutInfo.visibleItemsInfo[firstVisibleItemIndex]

            val viewWidth = this.size.width
            val itemWidth = firstVisibleItem.size
            if (state.layoutInfo.visibleItemsInfo.size * itemWidth > viewWidth) {
                val contentWidth = (state.layoutInfo.totalItemsCount * itemWidth)

                val scrollbarHeight = (viewWidth * (viewWidth / contentWidth )).coerceIn(10.gdp.toPx() .. viewWidth)
                val stepXOffsetSize = 1 / state.layoutInfo.totalItemsCount.toFloat() * viewWidth
                val itemOffset = stepXOffsetSize * ((-firstVisibleItem.offset.toFloat()) / itemWidth)
                val scrollbarXOffset = ((firstVisibleItem.index.toFloat() / state.layoutInfo.totalItemsCount.toFloat()) * viewWidth) + itemOffset

                drawRoundRect(
                    cornerRadius = CornerRadius(scrollbarWidth.toPx() / 2, scrollbarWidth.toPx() / 2),
                    color = color,
                    topLeft = Offset(scrollbarXOffset, this.size.height - scrollbarWidth.toPx()),
                    size = Size(scrollbarHeight, scrollbarWidth.toPx()),
                    alpha = alpha
                )
            }
        }
    }
}

@Composable
fun Modifier.verticalScrollbar(
    state: ScrollState,
    scrollbarWidth: Dp = 10.gdp,
    color: Color = GPColor.TextLightGray
): Modifier{
    val alpha by animateFloatAsState(targetValue = if(state.isScrollInProgress) 1f else 0.3f,
        animationSpec = tween(200, delayMillis = if(state.isScrollInProgress) 0 else 400),
        label = ""
    )

    return this then Modifier.drawWithContent {
        drawContent()

        val viewHeight = this.size.height
        val contentHeight = state.maxValue + viewHeight

        val scrollbarHeight = (viewHeight * (viewHeight / contentHeight )).coerceIn(10.gdp.toPx() .. viewHeight)
        val variableZone = viewHeight - scrollbarHeight
        val scrollbarYoffset = (state.value.toFloat() / state.maxValue) * variableZone

        drawRoundRect(
            cornerRadius = CornerRadius(scrollbarWidth.toPx() / 2, scrollbarWidth.toPx() / 2),
            color = color,
            topLeft = Offset(this.size.width - scrollbarWidth.toPx(), scrollbarYoffset),
            size = Size(scrollbarWidth.toPx(), scrollbarHeight),
            alpha = alpha
        )
    }
}