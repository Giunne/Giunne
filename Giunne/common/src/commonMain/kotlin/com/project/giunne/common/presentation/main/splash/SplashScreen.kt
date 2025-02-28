package com.project.giunne.common.presentation.main.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.presentation.common.player.IntroVideoPlayer

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
                .background(GPColor.BackgroundBlack)
        ) {
            IntroVideoPlayer(
                modifier = Modifier.fillMaxSize(),
                videoPath = "file:///Users/heeung/Desktop/giwoonnae_intro.mp4",
                onFullScreenClicked = {  },
            )
        }
    }
}