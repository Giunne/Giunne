package com.project.giunne.common.presentation.community.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import com.project.giunne.Res
import com.project.giunne.common.presentation.certification.student.state.CertPage
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.presentation.common.textfield.GPTextField
import com.project.giunne.common.presentation.community.student.state.DatePriority
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_search
import org.jetbrains.compose.resources.painterResource

@Composable
fun SearchRow(
    modifier: Modifier = Modifier,
    pageType: CertPage,
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    datePriority: DatePriority,
    roadmapFilter: String,
    runningFilter: String,
    onSearchButtonClicked: () -> Unit,
    onDatePriorityButtonClicked: () -> Unit,
    onRoadmapFilterButtonClicked: () -> Unit,
    onRunningFilterButtonClicked: () -> Unit,
) {
//    var searchText by remember { mutableStateOf("") } /* TODO MVI */

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        GPTextField(
            modifier = Modifier
                .padding(vertical = 4.gdp)
                .shadow(
                    2.gdp,
                    RoundedCornerShape(12.gdp)
                )
                .weight(152f)
                .background(color = GPColor.White),
            shape = RoundedCornerShape(12.gdp),
            textStyle = TextStyle(
                color = GPColor.TextBlack,
                fontSize = 14.gsp,
                fontFamily = GPFontFamily.Medium
            ),
            value = searchText,
            onValueChange = { onSearchTextChanged(it) },
            paddingHorizontal = 10.gdp,
            placeholder = {
                GPText(
                    text = "이름",
                    textSize = 14.gsp,
                    fontFamily = GPFontFamily.Medium,
                    textColor = GPColor.TextLightGray
                )
            },
            prefix = {
                Image(
                    modifier = Modifier.size(16.gdp),
                    painter = painterResource(Res.drawable.icon_search),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = GPColor.ButtonGray),
                    contentScale = ContentScale.FillHeight
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchButtonClicked()
                }
            ),
        )
        SpW(6.gdp)
        FilterBox(
            modifier = Modifier
                .weight(80f),
            content = when (pageType) {
                CertPage.RoadMap -> { roadmapFilter }
                CertPage.Running -> { runningFilter }
            },
            onClick = {
                when (pageType) {
                    CertPage.RoadMap -> { onRoadmapFilterButtonClicked() }
                    CertPage.Running -> { onRunningFilterButtonClicked() }
                }
            }
        )
        SpW(6.gdp)
        FilterBox(
            modifier = Modifier
                .weight(80f),
            content = when (datePriority) {
                DatePriority.NEWEST -> "최신순"
                DatePriority.OLDEST -> "오래된순"
            },
            onClick = { onDatePriorityButtonClicked() },
        )
    }
}