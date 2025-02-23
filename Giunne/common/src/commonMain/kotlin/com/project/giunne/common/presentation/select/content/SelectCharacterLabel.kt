package com.project.giunne.common.presentation.select.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun SelectCharacterLabel(
    name: String
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = GPColor.MainOrangeColor,
                shape = RoundedCornerShape(bottomEnd = 8.gdp, bottomStart = 8.gdp)
            )
            .padding(horizontal = 14.gdp, vertical = 6.gdp)
    ) {
        GPText(
            text = name,
            textColor = GPColor.White,
            textSize = 13.gsp
        )
    }
}