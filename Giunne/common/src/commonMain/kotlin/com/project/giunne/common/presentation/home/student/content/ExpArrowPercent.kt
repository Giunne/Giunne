package com.project.giunne.common.presentation.home.student.content

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import com.project.giunne.icon_point_arrow
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt

@Composable
fun ExpArrowPercent(
    percent: Float
) {
    var progress by remember { mutableFloatStateOf(0f) }
    var width by remember { mutableStateOf(0f) }
    val size by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 200,
            easing = LinearOutSlowInEasing
        ),
        label = "levelProgressAnimation"
    )

    LaunchedEffect(Unit) {
        progress = percent
    }

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.fillMaxWidth(size))
        Column(
            modifier = Modifier
                .wrapContentSize()
                .onSizeChanged {
                    width = it.width.toFloat() / 2
                }
//                .onGloballyPositioned { coordinates ->
//                    width = coordinates.size.width.toFloat() / 2
////                    println(width)
//                }
                .offset(x = -(width).roundToInt().gdp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            GPText(
                text = "${(percent * 100).toInt()}%",
                textSize = 12.gsp,
            )
            Spacer(modifier = Modifier.height(2.gdp))
            Icon(
                painter = painterResource(Res.drawable.icon_point_arrow),
                contentDescription = "경험치 포인트",
                tint = GPColor.TextBlack
            )
        }
    }
}