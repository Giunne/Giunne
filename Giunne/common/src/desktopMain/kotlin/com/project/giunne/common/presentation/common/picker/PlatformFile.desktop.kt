package com.project.giunne.common.presentation.common.picker

import java.io.File

actual class PlatformFile(
    private val file: File?
) {
    actual fun getPath(): String? {
        return "file:///" + file?.absolutePath
    }
}