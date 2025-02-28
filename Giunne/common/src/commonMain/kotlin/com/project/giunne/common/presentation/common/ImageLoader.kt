package com.project.giunne.common.presentation.common

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.memory.MemoryCache
import coil3.network.ktor2.KtorNetworkFetcherFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.utils.EmptyContent.headers
import java.util.Base64

fun newImageLoader(
    context: PlatformContext,
    debug: Boolean = false,
): ImageLoader {
    val authHeader = "Basic " + Base64.getEncoder().encodeToString("giunne_guest:ASDFqwer1234!".toByteArray())
    val httpClient = HttpClient {
        install(DefaultRequest) {
            headers.append("Authorization", authHeader) // 기본 헤더 추가
        }
    }

    return ImageLoader.Builder(context)
        .components {
            add(
                KtorNetworkFetcherFactory(httpClient)
            )
        }
        .memoryCache {
            MemoryCache.Builder()
                // Set the max size to 25% of the app's available memory.
                .maxSizePercent(context, percent = 0.25)
                .build()
        }
        // Show a short crossfade when loading images asynchronously.
        .crossfade(true)
        // Enable logging if this is a debug build.
        .apply {
            if (debug) {
                logger(DebugLogger())
            }
        }
        .build()
}