package com.project.giunne.common.presentation.common.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.presentation.common.text.GPTitleText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp

@Composable
fun GPMainTopBar(
    titleText: String? = null,
    rightIcon: @Composable () -> Unit = {},
    leftIcon: @Composable () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.gdp)
            .background(color = GPColor.BackgroundLightGray),
//            .padding(horizontal = 16.gdp),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier.align(Alignment.CenterStart)) {
            leftIcon()
        }

        if (titleText != null) {
            GPTitleText(
                text = titleText,
                textSize = 18.gsp,
                textColor = GPColor.TextBlack,
                fontFamily = GPFontFamily.Bold
            )
        }

        Box(modifier = Modifier.align(Alignment.CenterEnd)){
            rightIcon()
        }
    }
}