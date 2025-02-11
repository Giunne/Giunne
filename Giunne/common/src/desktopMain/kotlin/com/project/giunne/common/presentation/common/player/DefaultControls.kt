package com.project.giunne.common.presentation.common.player

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.GPFontFamily
import com.project.giunne.common.util.NotoSans
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import java.util.concurrent.TimeUnit
import kotlin.math.roundToLong

@Composable
fun DefaultControls(
    modifier: Modifier = Modifier,
    controller: PlayerController
) {
    val state by controller.state.collectAsState()

    val animatedTimestamp by animateFloatAsState(state.timestamp.toFloat())

    Column(
        modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Slider(
            colors = androidx.compose.material.SliderDefaults.colors(
                thumbColor = GPColor.TextBlack
            ),
            value = animatedTimestamp,
            onValueChange = { controller.seekTo(it.roundToLong()) },
            valueRange = 0f..state.duration.toFloat(),
            modifier = Modifier.fillMaxWidth().padding(4.dp)
        )
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            GPText(
                text = state.timestamp.formatTimestamp() + " / " + state.duration.formatTimestamp(),
                textSize = 12.gsp
            )
            Spacer(modifier = Modifier.width(80.gdp))
            if (state.isPlaying) {
                GPButton(
                    modifier = Modifier
                        .height(52.gdp)
                        .width(140.gdp),
                    normalColor = GPColor.ButtonBlack,
                    pressColor = GPColor.ButtonPressBlack,
                    hoverColor = GPColor.ButtonHoverBlack,
                    onClick = { controller.pause() },
                ) {
                    GPText(
                        text = "PAUSE",
                        textColor = GPColor.White,
                        fontFamily = GPFontFamily.Medium,
                        textAlign = TextAlign.Center,
                        textSize = 16.gsp,
                    )
                }
            } else {
                GPButton(
                    modifier = Modifier
                        .height(52.gdp)
                        .width(140.gdp),
                    normalColor = GPColor.ButtonBlack,
                    pressColor = GPColor.ButtonPressBlack,
                    hoverColor = GPColor.ButtonHoverBlack,
                    onClick = { controller.play() },
                ) {
                    GPText(
                        text = "PAUSE",
                        textColor = GPColor.White,
                        fontFamily = GPFontFamily.Medium,
                        textAlign = TextAlign.Center,
                        textSize = 16.gsp,
                    )
                }
            }
//            Box(Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
//                Row(
//                    horizontalArrangement = Arrangement.Center,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    if (state.isMuted || state.volume == 0f) IconButton(controller::toggleSound) {
//                        Icon(Icons.Rounded.Add, "volume off")
//                    }
//                    else {
//                        if (state.volume < .5f) IconButton(controller::toggleSound) {
//                            Icon(Icons.Rounded.Call, "volume low")
//                        } else IconButton(controller::toggleSound) {
//                            Icon(Icons.Rounded.Phone, "volume high")
//                        }
//                    }
//                    Slider(
//                        value = state.volume,
//                        onValueChange = controller::setVolume,
//                        modifier = Modifier.width(128.dp)
//                    )
//                }
//            }
        }
    }
}

@Suppress("DefaultLocale")
fun Long.formatTimestamp(): String {
    val hours = TimeUnit.MILLISECONDS.toHours(this)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(this) - TimeUnit.HOURS.toMinutes(hours)
    val seconds = TimeUnit.MILLISECONDS.toSeconds(this) - TimeUnit.MINUTES.toSeconds(minutes) - TimeUnit.HOURS.toSeconds(hours)
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}