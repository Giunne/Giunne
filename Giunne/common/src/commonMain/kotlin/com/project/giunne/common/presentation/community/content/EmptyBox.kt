package com.project.giunne.common.presentation.community.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.Res
import com.project.giunne.character_cat_level_2
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import org.jetbrains.compose.resources.painterResource

@Composable
fun EmptyBox() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GPColor.BackgroundLightGray),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(128.gdp),
                painter = painterResource(Res.drawable.character_cat_level_2),
                contentDescription = null
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                GPText(
                    text = "게시판이 비어있어요",
                    textSize = 16.gsp,
                    fontFamily = GPFontFamily.Bold,
                    textColor = GPColor.TextBlack
                )
            }
        }
    }
}