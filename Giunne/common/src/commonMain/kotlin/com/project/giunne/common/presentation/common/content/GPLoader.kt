package com.project.giunne.common.presentation.common.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.project.giunne.Res
import com.project.giunne.common.presentation.common.noRippleClickableWithoutHover
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.gdp
import com.project.giunne.image_loader_1
import com.project.giunne.image_loader_10
import com.project.giunne.image_loader_11
import com.project.giunne.image_loader_12
import com.project.giunne.image_loader_2
import com.project.giunne.image_loader_3
import com.project.giunne.image_loader_4
import com.project.giunne.image_loader_5
import com.project.giunne.image_loader_6
import com.project.giunne.image_loader_7
import com.project.giunne.image_loader_8
import com.project.giunne.image_loader_9
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource


@Composable
fun Loader(
    modifier: Modifier = Modifier
) {
    val imageList = listOf(
        painterResource(Res.drawable.image_loader_1),
        painterResource(Res.drawable.image_loader_2),
        painterResource(Res.drawable.image_loader_3),
        painterResource(Res.drawable.image_loader_4),
        painterResource(Res.drawable.image_loader_5),
        painterResource(Res.drawable.image_loader_6),
        painterResource(Res.drawable.image_loader_7),
        painterResource(Res.drawable.image_loader_8),
        painterResource(Res.drawable.image_loader_9),
        painterResource(Res.drawable.image_loader_10),
        painterResource(Res.drawable.image_loader_11),
        painterResource(Res.drawable.image_loader_12)
    )

    var image by remember { mutableStateOf(imageList[0]) }

    LaunchedEffect(Unit) {
        var index = 0
        while (true) {
            image = imageList[index % 12]
            index++
            delay(300)
        }
    }

    Dialog(
        onDismissRequest = {  },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(GPColor.BackgroundLoading)
                .noRippleClickableWithoutHover{},
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                    .size(52.gdp)
                    .background(GPColor.White, CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    modifier = Modifier.size(40.gdp),
                    painter = image,
                    contentDescription = null
                )
            }
//            CircularProgressIndicator(
//                modifier = Modifier
//                    .width(40.gdp),
//                color = GPColor.White,
//                trackColor = GPColor.ButtonGray,
//                strokeWidth = 6.gdp
//            )
        }
    }
}