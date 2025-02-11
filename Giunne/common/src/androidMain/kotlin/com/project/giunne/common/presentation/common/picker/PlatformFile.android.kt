package com.project.giunne.common.presentation.common.picker

import android.net.Uri

actual class PlatformFile(
    private val uri: Uri?
) {
    actual fun getPath(): String? {
        return uri.toString()
    }
}