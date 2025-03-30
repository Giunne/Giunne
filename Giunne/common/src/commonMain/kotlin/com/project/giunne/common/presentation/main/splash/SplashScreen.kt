package com.project.giunne.common.presentation.main.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.Res
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.presentation.common.player.IntroVideoPlayer
import com.project.giunne.common.util.gdp
import com.project.giunne.image_logo_rb
import org.jetbrains.compose.resources.painterResource

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(GPColor.BackgroundLightGray),
            contentAlignment = Alignment.Center
        ) {
            Image( // 로고 이미지
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(horizontal = 32.gdp),
                painter = painterResource(Res.drawable.image_logo_rb),
                contentDescription = "Giunnae Logo"
            )
//            IntroVideoPlayer(
//                modifier = Modifier.fillMaxSize(),
//                videoPath = "file:///Users/heeung/Desktop/giwoonnae_intro.mp4",
//                onFullScreenClicked = {  },
//            )
        }
    }
}