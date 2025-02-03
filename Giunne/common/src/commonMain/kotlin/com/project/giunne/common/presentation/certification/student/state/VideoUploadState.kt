package com.project.giunne.common.presentation.certification.student.state

import com.project.giunne.common.presentation.common.picker.PlatformFile

data class VideoUploadState(
    val videoFile: PlatformFile? = null,
    val videoPicker: Boolean = false,
    val videoPlayer: Boolean = false,
)
