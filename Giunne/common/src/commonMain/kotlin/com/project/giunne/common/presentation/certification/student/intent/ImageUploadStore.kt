package com.project.giunne.common.presentation.certification.student.intent

import com.project.giunne.common.presentation.certification.student.state.ImageUploadState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import com.project.giunne.common.presentation.common.picker.PlatformFile

class ImageUploadStore(
    val scope: CoroutineScope
) {
    var state = MutableStateFlow(ImageUploadState())
        private set
    private val currentState: ImageUploadState
        get() = state.value

    fun initImageFile(
        imageFile: PlatformFile
    ) {
        setState {
            copy(imageFile = imageFile)
        }
    }

    fun onClickImageUploadButton() {
        setState {
            copy(imagePicker = true)
        }
    }

    fun onClickImageExpandButton() {
        setState {
            copy(imageViewer = true)
        }
    }

    fun onClickImageResetButton() {
        setState {
            copy(imageFile = null)
        }
    }

    fun dismissImagePicker() {
        setState {
            copy(imagePicker = false)
        }
    }

    fun dismissImageViewer() {
        setState {
            copy(imageViewer = false)
        }
    }

    private inline fun setState(
        crossinline reduce: ImageUploadState.() -> ImageUploadState
    ) {
        state.update { currentState.reduce() }
    }
}