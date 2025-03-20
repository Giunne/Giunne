package com.project.giunne.common.presentation.shop.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImagePainter.State.Empty.painter
import com.project.giunne.Res
import com.project.giunne.character_cat_level_1
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import org.jetbrains.compose.resources.painterResource

@Composable
fun EmptyItemList(
    modifier: Modifier,
    description: String
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(40.gdp),
                painter = painterResource(Res.drawable.character_cat_level_1),
                contentDescription = "고양이 1레벨"
            )
            Spacer(modifier = Modifier.height(8.gdp))
            GPText(
                text = description,
                textSize = 12.gsp
            )
        }
    }
}