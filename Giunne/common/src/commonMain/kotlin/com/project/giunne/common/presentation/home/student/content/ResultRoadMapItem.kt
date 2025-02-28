package com.project.giunne.common.presentation.home.student.content

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.project.giunne.common.data.remote.response.Recreation
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun ResultRoadMapItem(
    modifier: Modifier,
    isSelected: Boolean = true,
    recreation: Recreation,
    onItemSelected: () -> Unit = {}
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(12.gdp))
            .background(GPColor.White)
            .border(
                width = if (isSelected) 2.gdp else 1.gdp,
                color = if (isSelected) GPColor.MainOrangeColor else GPColor.ButtonLightGray,
                shape = RoundedCornerShape(12.gdp)
            )
            .padding(horizontal = 24.gdp, vertical = 20.gdp)
            .noRippleClickable { onItemSelected() },
        verticalArrangement = Arrangement.spacedBy(8.gdp)
    ) {
        GPText(
            text = "선생님",
            textSize = 11.gsp,
            textColor = GPColor.TextGray
        )
        GPText(
            text = recreation.teacherName,
            textSize = 14.gsp,
            fontFamily = GPFontFamily.Bold
        )
        HorizontalDivider(
            color = GPColor.ButtonLightGray
        )
        GPText(
            text = "로드맵",
            textSize = 10.gsp,
            textColor = GPColor.TextGray
        )
        GPText(
            text = recreation.recreationName,
            textSize = 14.gsp,
            fontFamily = GPFontFamily.Bold
        )
    }
}