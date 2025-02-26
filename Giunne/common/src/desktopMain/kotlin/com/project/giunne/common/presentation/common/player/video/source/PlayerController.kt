package com.project.giunne.common.presentation.common.player.video.source

import kotlinx.coroutines.flow.StateFlow

interface PlayerController {
    val state: StateFlow<PlayerState>
    fun load(url: String)
    fun play()
    fun pause()
    fun stop()
    fun dispose()
    fun seekTo(timestamp: Long)
    fun setVolume(value: Float)
    fun toggleSound()
    fun rotate(value: Float)
}