package com.project.giunne.common.presentation.common.player

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.project.giunne.R
import com.project.giunne.common.presentation.common.noRippleClickable
import com.project.giunne.common.ui.theme.GPColor
import com.project.giunne.common.util.BackHandler
import kotlinx.coroutines.delay

@Composable
actual fun VideoWindowPlayer(
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

@Composable
actual fun VideoPlayer(
    modifier: Modifier,
    videoPath: String,
    onFullScreenClicked: () -> Unit
) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(videoPath)
            setMediaItem(mediaItem)
            prepare()
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            modifier = modifier
                .fillMaxHeight()
                .wrapContentWidth(),
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                }
            },
            update = {}
        )
    }
}

@Composable
actual fun IntroVideoPlayer(
    modifier: Modifier,
    videoPath: String,
    onFullScreenClicked: () -> Unit
) {
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val videoUri = Uri.parse("android.resource://com.project.giunne/${R.raw.giwoonnae_intro}")
            val mediaItem = MediaItem.fromUri(videoUri)
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }

    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) exoPlayer.pause()
            if (event == Lifecycle.Event.ON_START) exoPlayer.play()
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
            exoPlayer.release()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(GPColor.BackgroundLightGray),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            modifier = modifier,
//                .fillMaxSize(),
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                    useController = false
                }
            },
            update = {}
        )
    }
}