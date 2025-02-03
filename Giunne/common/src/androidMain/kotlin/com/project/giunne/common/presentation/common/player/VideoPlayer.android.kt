package com.project.giunne.common.presentation.common.player

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.util.BackHandler
import kotlinx.coroutines.delay

@Composable
actual fun VideoPlayer(
    modifier: Modifier,
    dismiss: () -> Unit,
    videoPath: String,
) {
    var backPress by remember { mutableStateOf(false) }
    BackHandler {
        backPress = true
    }

    LaunchedEffect(backPress) {
        if (backPress) {
            dismiss()
        }
    }

    Dialog(
        onDismissRequest = { dismiss() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .noRippleClickable {
                    dismiss()
                },
            contentAlignment = Alignment.Center
        ) {
            AndroidView(
                modifier = modifier
                    .fillMaxHeight()
                    .wrapContentWidth(),
                factory = { context ->
                    PlayerView(context).apply {
                        val exoPlayer = ExoPlayer.Builder(context).build()
                        exoPlayer.setMediaItem(androidx.media3.common.MediaItem.fromUri(videoPath))
                        player = exoPlayer
                        exoPlayer.prepare()

                        useController = true
                    }
                },
                update = {}
            )
        }
    }
}