package com.project.giunne.common.presentation.roadmap.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.Res
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
    Row(
        modifier = modifier
            .wrapContentSize()
            .clickable {
                openYoutubeLink()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        GPText(
            text = title,
            textSize = 18.gsp
        )

        Spacer(modifier = Modifier.width(8.gdp))

        Image(
            modifier = Modifier
                .width(28.gdp)
                .wrapContentHeight(),
            painter = painterResource(Res.drawable.image_youtube),
            contentDescription = ""
        )
    }
}