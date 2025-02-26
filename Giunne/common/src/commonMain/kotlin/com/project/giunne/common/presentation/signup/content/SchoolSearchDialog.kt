package com.project.giunne.common.presentation.signup.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.project.giunne.Res
import com.project.giunne.common.data.remote.response.SchoolInfo
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.button.GPIconButton
import com.project.giunne.common.presentation.common.scrollbar.VerticalScrollbar
import com.project.giunne.common.presentation.common.spacer.SpH
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.common.textfield.GPTextField
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_search
import org.jetbrains.compose.resources.painterResource

private const val TAG = "SchoolSearchDialog"
@Composable
fun SchoolSearchDialog(
    modifier: Modifier = Modifier,
    dismiss: () -> Unit,
    onClickConfirmButton: (SchoolInfo) -> Unit,
    onClickSearchButton: (String) -> Unit,
    searchList: List<SchoolInfo> = emptyList(),
//    pageNationInfo: PageNationInfo,
//    callSchoolList: (searchText: String, pageIndex: Int, pageSize: Int) -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    var selectedItemIndex by remember { mutableStateOf<Int?>(null) }

    val listState = rememberLazyListState()

//    val endOfListReached by remember(pageNationInfo) {
//        derivedStateOf {
//            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
//            lastVisibleItem != null && pageNationInfo.hasNextPage
//        }
//    }
//
//    LaunchedEffect(endOfListReached) {
//        GLog.d(TAG, "$pageNationInfo")
//
//        if (endOfListReached && pageNationInfo.currentPage != pageNationInfo.totalPage) {
//            callSchoolList(searchText, pageNationInfo.currentPage + 1, pageNationInfo.pageSize)
//        }
//    }

    Dialog(
        onDismissRequest = { dismiss() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        )
    ) {
        Card(
            modifier = Modifier
                .background(GPColor.White, RoundedCornerShape(12.gdp))
                .width(320.gdp)
                .wrapContentHeight()
                .padding(vertical = 10.gdp, horizontal = 16.gdp),
            shape = RoundedCornerShape(12.gdp)
        ) {
            Column(
                modifier = Modifier
                    .background(GPColor.White)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.gdp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    GPText(
                        text = "학교 검색",
                        textSize = 18.gsp,
                        textColor = GPColor.ButtonBlack,
                        fontFamily = GPFontFamily.Regular
                    )
                }

                Column(
                    modifier = Modifier
                        .heightIn(min = 120.gdp, max = 400.gdp)
                        .fillMaxWidth()
                ) {
                    GPTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.gdp)
                            .background(color = GPColor.White),
                        shape = RoundedCornerShape(10.gdp),
                        textStyle = TextStyle(
                            color = GPColor.TextBlack,
                            fontSize = 14.gsp,
                            fontFamily = GPFontFamily.Medium
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = { onClickSearchButton(searchText) }
                        ),
                        placeholder = {
                            GPText(
                                text = "ex) 성남초",
                                textColor = GPColor.TextLightGray,
                                textSize = 14.gsp,
                                fontFamily = GPFontFamily.Medium
                            )
                        },
                        value = searchText,
                        onValueChange = { searchText = it },
                        border = true,
                        suffix = {
                            GPIconButton(
                                modifier = Modifier.size(28.gdp),
                                icon = {
                                    Image(
                                        modifier = Modifier.size(16.gdp),
                                        painter = painterResource(Res.drawable.icon_search),
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(GPColor.White)
                                    )
                                },
                                normalColor = GPColor.ButtonOrange,
                                pressColor = GPColor.ButtonPressOrange,
                                hoverColor = GPColor.ButtonHoverOrange,
                                onClick = {
                                    onClickSearchButton(searchText)
                                },
                            )
                        }
                    )
                    SpH(10.gdp)
                    if (searchList.isNotEmpty()) {
                        Box {
                            LazyColumn(
                                state = listState
                            ) {
                                items(
                                    count = searchList.size
                                ) {
                                    SearchItem(
                                        schoolInfo = searchList[it],
                                        onClickItem = { selectedItemIndex = it },
                                        isSelected = if (selectedItemIndex != null) it == selectedItemIndex else false
                                    )
                                }
                            }
                            VerticalScrollbar(
                                modifier = Modifier.align(Alignment.CenterEnd),
                                state = listState
                            )
                        }
                    } else {
                        GPText(
                            text = "학교명으로 검색하세요. 공백 없이 검색해주세요.",
                            textColor = GPColor.TextGray,
                            textSize = 12.gsp,
                            fontFamily = GPFontFamily.Medium
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .height(68.gdp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GPButton(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.gdp),
                        normalColor = GPColor.ButtonLightGray,
                        pressColor = GPColor.ButtonPressLightGray,
                        hoverColor = GPColor.ButtonHoverLightGray,
                        onClick = { dismiss() },
                    ) {
                        GPText(
                            text = "취소",
                            textSize = 14.gsp,
                            fontFamily = GPFontFamily.Bold,
                            textColor = GPColor.White
                        )
                    }
                    SpW(10.gdp)
                    GPButton(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.gdp),
                        normalColor = GPColor.ButtonOrange,
                        pressColor = GPColor.ButtonPressOrange,
                        hoverColor = GPColor.ButtonHoverOrange,
                        onClick = {
                            if (selectedItemIndex != null) {
                                onClickConfirmButton(searchList[selectedItemIndex!!])
                            }
                        },
                    ) {
                        GPText(
                            text = "확인",
                            textSize = 14.gsp,
                            fontFamily = GPFontFamily.Bold,
                            textColor = GPColor.White
                        )
                    }
                }
            }
        }
    }
}