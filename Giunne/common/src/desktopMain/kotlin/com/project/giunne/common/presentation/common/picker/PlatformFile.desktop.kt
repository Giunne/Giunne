package com.project.giunne.common.presentation.common.picker

import java.io.File
import java.nio.file.Files

actual class PlatformFile(
    private val file: File?
) {
    actual fun getPath(): String? {
        return "file:///" + file?.absolutePath
    }

    actual fun toByteArray(): ByteArray {
        return file?.readBytes() ?: byteArrayOf()
    }

    actual fun getMimeType(): String {
        return file?.let { Files.probeContentType(it.toPath()) }.orEmpty()
    }
}