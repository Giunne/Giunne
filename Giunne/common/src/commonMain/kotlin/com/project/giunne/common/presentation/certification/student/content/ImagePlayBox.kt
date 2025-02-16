package com.project.giunne.common.presentation.certification.student.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.icon_close
import com.project.giunne.icon_expand
import org.jetbrains.compose.resources.painterResource
import com.project.giunne.common.presentation.common.picker.PlatformFile

@Composable
fun ImagePlayBox(
    modifier: Modifier = Modifier,
    image: PlatformFile,
    onExpandButtonClicked: () -> Unit,
    onResetButtonClicked: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.gdp)),
            model = image.getPath(),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.gdp)
                    .background(
                        color = GPColor.White,
                        shape = CircleShape
                    )
                    .noRippleClickable { onExpandButtonClicked() },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(20.gdp),
                    painter = painterResource(Res.drawable.icon_expand),
                    contentDescription = "ExpandButton"
                )
            }
            SpW(20.gdp)
            Box(
                modifier = Modifier
                    .size(52.gdp)
                    .background(
                        color = GPColor.White,
                        shape = CircleShape
                    )
                    .noRippleClickable { onResetButtonClicked() },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(20.gdp),
                    painter = painterResource(Res.drawable.icon_close),
                    contentDescription = "ResetButton"
                )
            }
        }
    }
}