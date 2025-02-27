package com.project.giunne.common.presentation.home.student.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.giunne.common.presentation.common.addFocusCleaner
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.content.Loader
import com.project.giunne.common.presentation.common.search.GPSearchBar
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.home.student.content.ResultRoadMapItem
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun SearchRoadMapScreen(
    component: SearchRoadMapComponent,
    onBackClick: () -> Unit,
    navigateToHome: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val lazyListState = rememberLazyListState()
    val searchState by component.uiState.collectAsStateWithLifecycle()
    var selectedItemIndex by remember { mutableIntStateOf(-1) }
    var searchText by remember { mutableStateOf("") }
    val isEnabled by remember {
        derivedStateOf {
            selectedItemIndex != -1
        }
    }

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.layoutInfo }
            .collect { layoutInfo ->
                val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                val totalItemsCount = layoutInfo.totalItemsCount

                if (searchState.paginationInfo.hasNextPage && lastVisibleItemIndex >= totalItemsCount - 1) {
                    component.searchRecreation(searchText, searchState.paginationInfo.currentPage + 1)
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GPColor.BackgroundLightGray)
            .addFocusCleaner(focusManager)
            .verticalScroll(rememberScrollState())
    ) {
        if (searchState.isLoading) {
            Loader()
        }
        GPSearchBar(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            focusManager = focusManager,
            searchText = searchText,
            onSearchTextChange = {
                searchText = it
            },
            onSearchQuery = {
                component.searchRecreation(searchText, 1)
            },
            onClear = {
                searchText = ""
            },
            placeHolder = "검색할 로드맵을 입력해주세요."
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(searchState.searchRecreationList.size) { index ->
                ResultRoadMapItem(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    isSelected = selectedItemIndex == index,
                    recreation = searchState.searchRecreationList[index],
                    onItemSelected = {
                        selectedItemIndex = if (selectedItemIndex == index) {
                            -1
                        } else {
                            index
                        }
                        focusManager.clearFocus()
                    }
                )
            }
        }

        GPButton(
            modifier = Modifier
                .padding(16.gdp)
                .fillMaxWidth()
                .height(56.gdp),
            normalColor = if (isEnabled) GPColor.ButtonOrange else GPColor.ButtonLightGray,
            pressColor = if (isEnabled) GPColor.ButtonPressOrange else GPColor.ButtonLightGray,
            hoverColor = if (isEnabled) GPColor.ButtonHoverOrange else GPColor.ButtonLightGray,
            onClick = {
                if (isEnabled) {
                    navigateToHome()
                }
            },
        ) {
            GPText(
                text = "참여하기",
                textSize = 14.gsp,
                fontFamily = GPFontFamily.Bold,
                textColor = GPColor.White
            )
        }
    }
}