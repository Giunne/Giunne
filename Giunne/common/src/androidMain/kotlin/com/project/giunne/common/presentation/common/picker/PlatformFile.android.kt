package com.project.giunne.common.presentation.common.picker

import android.content.ContentResolver
import android.net.Uri

actual class PlatformFile(
    private val uri: Uri?,
    private val contentResolver: ContentResolver
) {
    actual fun getPath(): String? {
        return uri.toString()
    }

    actual fun toByteArray(): ByteArray {
        return uri?.let { contentResolver.openInputStream(it)?.readBytes() } ?: byteArrayOf()
    }

    actual fun getMimeType(): String {
        return uri?.let { contentResolver.getType(it) }.orEmpty()
    }
}