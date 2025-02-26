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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
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
    var selectedItem by remember { mutableIntStateOf(-1) }
    var searchText by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val isEnabled by remember {
        derivedStateOf {
            selectedItem != -1
        }
    }
    var testSearchList by remember { mutableStateOf(listOf<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GPColor.BackgroundLightGray)
            .addFocusCleaner(focusManager)
            .verticalScroll(rememberScrollState())
    ) {
        if (isLoading) {
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
                /*TODO(API 나오면 바꾸기)*/
                CoroutineScope(Dispatchers.IO).launch {
                    isLoading = true
                    delay(1000)
                    testSearchList = List(20) { "Test $it" }
                    isLoading = false
                }
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
            items(testSearchList.size) { index ->
                ResultRoadMapItem(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    isSelected = selectedItem == index,
                    teacherName = "홍길동",
                    description = "Test $index",
                    onItemSelected = {
                        selectedItem = if (selectedItem == index) {
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
                text = "참여요청",
                textSize = 14.gsp,
                fontFamily = GPFontFamily.Bold,
                textColor = GPColor.White
            )
        }
    }
}