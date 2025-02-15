package com.project.giunne.common.presentation.roadmap.content


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp

@Composable
fun BorderContentField(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .border(
                width = 2.gdp,
                color = GPColor.BackgroundGray_F6F6F6,
                shape = RoundedCornerShape(12.gdp)
            )
            .padding(horizontal = 16.gdp, vertical = 8.gdp),
    ) {
        content()
    }
}