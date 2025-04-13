package com.project.giunne.common.presentation.shop.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import com.project.giunne.common.data.util.DefineUrl
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.removeSpaceUrl
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

private const val DELAY_SCROLL = 8L
private const val SCROLL_X = 1f

@Composable
fun GachaItemList(
    gachaItems: List<String>
) {
    var itemListState = gachaItems
    val lazyListState = rememberLazyListState()

    LaunchedEffect(Unit) {
        autoScroll(lazyListState)
    }

    LazyRow(
        state = lazyListState,
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.gdp),
        userScrollEnabled = false
    ) {
        items(itemListState.size) {
            AsyncImage(
                modifier = Modifier
                    .width(120.gdp),
                model = DefineUrl.IMAGE_BASE_URL + gachaItems[it].removeSpaceUrl(),
                contentDescription = "아이템"
            )
            if (itemListState[it] == itemListState.last()) {
                val currentList = itemListState
                val secondPart = currentList.subList(0, lazyListState.firstVisibleItemIndex)
                val firstPart = currentList.subList(lazyListState.firstVisibleItemIndex, currentList.size)

                rememberCoroutineScope().launch {
                    lazyListState.scrollToItem(0, lazyListState.firstVisibleItemScrollOffset - SCROLL_X.toInt())
                }

                itemListState = firstPart + secondPart
            }
        }
    }
}

private suspend fun autoScroll(lazyListState: LazyListState) {
    lazyListState.scroll(MutatePriority.PreventUserInput) {
        scrollBy(SCROLL_X)
    }
    delay(DELAY_SCROLL)
    autoScroll(lazyListState)
}
