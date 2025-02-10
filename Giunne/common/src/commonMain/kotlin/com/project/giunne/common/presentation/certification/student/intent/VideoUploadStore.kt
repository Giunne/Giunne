package com.project.giunne.common.presentation.certification.student.intent

import com.project.giunne.common.presentation.certification.student.state.VideoUploadState
import com.project.giunne.common.presentation.common.picker.PlatformFile
import com.project.giunne.common.presentation.signup.state.InfoState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.io.File

class VideoUploadStore(
    val scope: CoroutineScope
) {
    var state = MutableStateFlow(VideoUploadState())
        private set
    private val currentState: VideoUploadState
        get() = state.value

    fun initVideoFile(
        videoFile: PlatformFile
    ) {
        setState {
            copy(videoFile = videoFile)
        }
    }

    fun onClickVideoUploadButton() {
        setState {
            copy(videoPicker = true)
        }
    }

    fun onClickVideoPlayButton() {
        setState {
            copy(videoPlayer = true)
        }
    }

    fun onClickVideoResetButton() {
        setState {
            copy(videoFile = null)
        }
    }

    fun dismissVideoPicker() {
        setState {
            copy(videoPicker = false)
        }
    }

    fun dismissVideoPlayer() {
        setState {
            copy(videoPlayer = false)
        }
    }

    private inline fun setState(
        crossinline reduce: VideoUploadState.() -> VideoUploadState
    ) {
        state.update { currentState.reduce() }
    }
}