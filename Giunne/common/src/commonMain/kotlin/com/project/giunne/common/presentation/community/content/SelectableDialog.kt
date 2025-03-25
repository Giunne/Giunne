package com.project.giunne.common.presentation.community.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.project.giunne.common.data.remote.response.QuestTypeInfo
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.common.presentation.common.scrollbar.VerticalScrollbar

@Composable
fun SelectableDialog(
    modifier: Modifier = Modifier,
    dismiss: () -> Unit,
    onSelect: (String) -> Unit,
    filterList: List<QuestTypeInfo>
) {
    val scrollState = rememberLazyListState()

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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 400.gdp)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(vertical = 16.gdp)
                            .fillMaxWidth()
                            .heightIn(max = 400.gdp),
                        state = scrollState
                    ) {
                        items(
                            count = filterList.size
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(42.gdp)
                                    .padding(horizontal = 16.gdp)
                                    .noRippleClickable {
                                        onSelect(filterList[it].questName)
                                    },
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                GPText(
                                    text = filterList[it].questName,
                                    textSize = 16.gsp,
                                    textColor = GPColor.TextBlack,
                                    fontFamily = GPFontFamily.Bold,
                                )
                            }
                        }
                    }
                    VerticalScrollbar(
                        modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                        state = scrollState
                    )
                }
                Row(
                    modifier = Modifier
                        .height(68.gdp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GPButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.gdp),
                        normalColor = GPColor.ButtonOrange,
                        pressColor = GPColor.ButtonPressOrange,
                        hoverColor = GPColor.ButtonHoverOrange,
                        onClick = { dismiss() },
                    ) {
                        GPText(
                            text = "닫기",
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