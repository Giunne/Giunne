package com.project.giunne.common.presentation.common.player.video.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.giunne.common.presentation.common.player.video.source.DefaultControls
import com.project.giunne.common.presentation.common.player.video.source.PlayerController
import java.awt.Component

@Composable
fun ComponentPlayer(
    modifier: Modifier = Modifier,
    url: String,
    component: Component,
    controller: PlayerController,
) {
    DisposableEffect(url) {
        controller.load(url)
        onDispose { controller.dispose() }
    }
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ComponentContainer(Modifier.weight(1f), component)
        DefaultControls(Modifier.fillMaxWidth(), controller)
    }
}