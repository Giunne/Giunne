package com.project.giunne.common.presentation.roadmap.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.image_youtube
import org.jetbrains.compose.resources.painterResource

@Composable
fun ExerciseHeaderLink(
    modifier: Modifier = Modifier,
    title: String,
    openYoutubeLink: () -> Unit
) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .noRippleClickable {
                openYoutubeLink()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.gdp)
    ) {
        Image(
            modifier = Modifier
                .width(32.gdp)
                .wrapContentHeight(),
            painter = painterResource(Res.drawable.image_youtube),
            contentDescription = ""
        )

        GPText(
            text = title,
            textSize = 16.gsp
        )
    }
}