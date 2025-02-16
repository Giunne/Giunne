package com.project.giunne.common.presentation.certification.student.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.presentation.common.spacer.SpW
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.icon_close
import com.project.giunne.icon_play
import org.jetbrains.compose.resources.painterResource

@Composable
fun VideoPlayBox(
    modifier: Modifier = Modifier,
    onPlayButtonClicked: () -> Unit,
    onResetButtonClicked: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
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
                    .noRippleClickable { onPlayButtonClicked() },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(20.gdp),
                    painter = painterResource(Res.drawable.icon_play),
                    contentDescription = "PlayButton"
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