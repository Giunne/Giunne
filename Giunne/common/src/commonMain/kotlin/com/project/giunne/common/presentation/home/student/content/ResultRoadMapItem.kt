package com.project.giunne.common.presentation.home.student.content

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun ResultRoadMapItem(
    modifier: Modifier,
    isSelected: Boolean,
    description: String,
    onItemSelected: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(12.gdp))
            .background(GPColor.White)
            .border(
                width = if (isSelected) 2.gdp else 0.gdp,
                color = if (isSelected) GPColor.MainOrangeColor else GPColor.Transparent,
                shape = RoundedCornerShape(12.gdp)
            )
            .padding(horizontal = 24.gdp, vertical = 20.gdp)
            .noRippleClickable { onItemSelected() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        GPText(
            text = description,
            textSize = 13.gsp,
        )
    }
}