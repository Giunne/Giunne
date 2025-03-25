package com.project.giunne.common.presentation.common.picker

expect class PlatformFile {
    fun getPath(): String?
    fun toByteArray(): ByteArray
    fun getMimeType(): String
}