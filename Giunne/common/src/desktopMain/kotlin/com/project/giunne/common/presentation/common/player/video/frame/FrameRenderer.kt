package com.project.giunne.common.presentation.common.player.video.frame

import kotlinx.coroutines.flow.StateFlow

interface FrameRenderer {
    val size: StateFlow<Pair<Int, Int>>
    val bytes: StateFlow<ByteArray?>
}