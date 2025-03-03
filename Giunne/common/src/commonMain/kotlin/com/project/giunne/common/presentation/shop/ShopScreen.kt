package com.project.giunne.common.presentation.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.charactor.GPShopCharacter
import com.project.giunne.common.presentation.common.content.Loader
import com.project.giunne.common.presentation.common.dialog.GPAlertDialog
import com.project.giunne.common.presentation.shop.content.ItemBottomView
import com.project.giunne.common.presentation.shop.intent.ShopStore
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.Define
import com.project.giunne.common.util.gdp
import kotlinx.coroutines.async

@Composable
internal fun ShopScreen(
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val lazyGridState = rememberLazyGridState()
    val shopStore by remember { mutableStateOf(ShopStore()) }
    val state by shopStore.uiState.collectAsState()

    LaunchedEffect(Unit) {
        async {
            shopStore.getCategoryMap()
            shopStore.onChangeType(2)
            shopStore.setCurrentWearingItems(Define.playerId, 1)
        }.await()
    }

    LaunchedEffect(lazyGridState) {
        snapshotFlow { lazyGridState.layoutInfo }
            .collect { layoutInfo ->
                val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                val totalItemsCount = layoutInfo.totalItemsCount

                if (state.paginationInfo.hasNextPage && lastVisibleItemIndex >= totalItemsCount - 1) {
                    shopStore.loadNextPage(state.selectedType, state.paginationInfo.currentPage + 1)
                }
            }
    }

    Scaffold(
        modifier = Modifier
            .addFocusCleaner(focusManager)
            .fillMaxSize()
            .imePadding(),
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(GPColor.BackgroundLightGray),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .height(256.gdp),
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    GPShopCharacter(
                        modifier = Modifier
                            .size(256.gdp),
                        currentLevel = state.currentLevel,
                        character = state.selectedCharacter,
                        items = state.selectedItems
                    )
                }
            }

            if (state.categoryMap.isEmpty()) {
                Loader()
            } else {
                ItemBottomView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    lazyGridState = lazyGridState,
                    types = state.categoryMap[0]?.drop(1) ?: listOf(),
                    shopStore = shopStore,
                    state = state,
                    onItemClick = { item ->
                        shopStore.onChangeItem(item)
                    },
                    onTypeSelected = { itemType ->
                        shopStore.onChangeType(itemType)
                    }
                )
            }
        }

        if (state.error != null) {
            GPAlertDialog(
                title = "꾸미기 에러",
                content = state.error!!.message.toString(),
                dismiss = {
                    shopStore.dismissErrorDialog()
                }
            )
        }
    }
}