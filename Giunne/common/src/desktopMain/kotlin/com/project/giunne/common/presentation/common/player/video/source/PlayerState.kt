package com.project.giunne.common.presentation.common.player.video.source

data class PlayerState(
    val isPlaying: Boolean = false,
    val isMuted: Boolean = false,
    val volume: Float = .5f,
    val timestamp: Long = 0L,
    val duration: Long = 0L,
)