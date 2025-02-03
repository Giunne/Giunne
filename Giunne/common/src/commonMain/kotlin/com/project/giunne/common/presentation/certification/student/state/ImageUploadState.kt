package com.project.giunne.common.presentation.certification.student.state

import com.project.giunne.common.presentation.common.picker.PlatformFile

data class ImageUploadState(
    val imageFile: PlatformFile? = null,
    val imagePicker: Boolean = false,
    val imageViewer: Boolean = false,
)
