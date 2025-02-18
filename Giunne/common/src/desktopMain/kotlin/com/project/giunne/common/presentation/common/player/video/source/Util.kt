package com.project.giunne.common.presentation.common.player.video.source

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.readBytes
import io.ktor.client.statement.request
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.Base64
import java.util.concurrent.TimeUnit
import kotlin.io.encoding.ExperimentalEncodingApi

fun catch(body: () -> Unit): Unit = runCatching { body() }.onFailure { println(it.localizedMessage) }.getOrNull() ?: Unit

fun Long.formatTimestamp(): String {
    val hours = TimeUnit.MILLISECONDS.toHours(this)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(this) - TimeUnit.HOURS.toMinutes(hours)
    val seconds = TimeUnit.MILLISECONDS.toSeconds(this) - TimeUnit.MINUTES.toSeconds(minutes) - TimeUnit.HOURS.toSeconds(hours)
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}