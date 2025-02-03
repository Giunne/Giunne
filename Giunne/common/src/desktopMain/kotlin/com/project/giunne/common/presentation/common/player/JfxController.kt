package com.project.giunne.common.presentation.common.player

import javafx.beans.value.ChangeListener
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.util.Duration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.roundToLong

class JfxController : PlayerController {

    internal var player: MediaPlayer? = null
        private set

    private val _state = MutableStateFlow(PlayerState())
    override val state: StateFlow<PlayerState> = _state.asStateFlow()

    private val statusListener = ChangeListener<MediaPlayer.Status> { _, _, newValue ->
        _state.update { it.copy(isPlaying = newValue == MediaPlayer.Status.PLAYING) }
    }

    private val durationListener = ChangeListener<Duration> { _, _, newValue ->
        _state.update { it.copy(duration = newValue.toMillis().roundToLong()) }
    }

    private val currentTimeListener = ChangeListener<Duration> { _, _, newValue ->
        _state.update { it.copy(timestamp = newValue.toMillis().roundToLong()) }
    }

    private val volumeListener = ChangeListener<Number> { _, _, newValue ->
        _state.update { it.copy(volume = newValue.toFloat()) }
    }

    override fun load(url: String): Unit = catch {
        player = MediaPlayer(Media(url)).apply {
            volume = .5
            statusProperty()?.addListener(statusListener)
            currentTimeProperty()?.addListener(currentTimeListener)
            cycleDurationProperty().addListener(durationListener)
            volumeProperty().addListener(volumeListener)
        }
    }

    override fun play(): Unit = catch {
        player?.play()
    }

    override fun pause(): Unit = catch {
        player?.pause()
    }

    override fun stop(): Unit = catch {
        player?.stop()
    }

    override fun dispose(): Unit = catch {
        player?.apply {
            statusProperty()?.removeListener(statusListener)
            currentTimeProperty()?.removeListener(currentTimeListener)
            cycleDurationProperty().removeListener(durationListener)
            volumeProperty().removeListener(volumeListener)
            stop()
            dispose()
        }
        player = null
    }

    override fun seekTo(timestamp: Long): Unit = catch {
        player?.seek(Duration(timestamp.toDouble()))
    }

    override fun setVolume(value: Float): Unit = catch {
        value.toDouble().coerceIn(0.0, 1.0).let { v -> player?.volume = v }
    }

    override fun toggleSound(): Unit = catch {
        player?.run {
            isMute = !isMute
            _state.update { it.copy(isMuted = isMute) }
        }
    }
}

fun catch(body: () -> Unit): Unit = runCatching { body() }.onFailure { println(it.localizedMessage) }.getOrNull() ?: Unit