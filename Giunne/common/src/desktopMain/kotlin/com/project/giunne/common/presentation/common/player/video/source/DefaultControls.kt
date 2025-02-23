package com.project.giunne.common.presentation.common.player.video.source

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.giunne.common.presentation.common.button.GPButton
import com.project.giunne.common.presentation.common.text.GPText
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.common.util.gsp
import kotlin.math.roundToLong

@Composable
fun DefaultControls(modifier: Modifier = Modifier, controller: PlayerController) {

    val state by controller.state.collectAsState()

    val animatedTimestamp by animateFloatAsState(state.timestamp.toFloat())

    Column(
        modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Slider(
            colors = SliderDefaults.colors(
                thumbColor = GPColor.White
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
                text = state.timestamp.formatTimestamp(),
                textSize = 20.gsp,
            )
            Spacer(modifier = Modifier.width(80.gdp))
            if (state.isPlaying) {
                GPButton(
                    modifier = modifier
                        .width(160.gdp)
                        .height(80.gdp),
                    normalColor = GPColor.ButtonBlack,
                    pressColor = GPColor.ButtonPressBlack,
                    shape = RoundedCornerShape(12.gdp),
                    onClick = controller::pause
                ) {
                    GPText(
                        text = "PAUSE",
                        textSize = 14.gsp,
                    )
                }
            } else {
                GPButton(
                    modifier = modifier
                        .width(160.gdp)
                        .height(80.gdp),
                    normalColor = GPColor.ButtonOrange,
                    pressColor = GPColor.ButtonPressOrange,
                    shape = RoundedCornerShape(12.gdp),
                    onClick = controller::play
                ) {
                    GPText(
                        text = "Play",
                        textSize = 14.gsp,
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