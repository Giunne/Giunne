package com.project.giunne.common.di

import com.project.giunne.common.data.util.DefineUrl
import com.project.giunne.common.util.Define
import de.jensklingenberg.ktorfit.ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule: Module = module {
    single(named("Auth")) {
        ktorfit {
            httpClient(
                createHttpClient(true)
            )
        }
    }

    single(named("Default")) {
        ktorfit {
            httpClient(
                createHttpClient()
            )
        }
    }
}

fun createHttpClient(
    auth: Boolean = false
): HttpClient {
    return HttpClient {
        install(HttpTimeout) {
            requestTimeoutMillis = 5000L
            connectTimeoutMillis = 5000L
        }
        install(ContentNegotiation) {
            json(
                json = Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                },
                contentType = ContentType.Application.Json
            )
        }
        install(DefaultRequest) {
            url(DefineUrl.BASE_URL)
            headers.apply {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                if (auth) {
                    header(HttpHeaders.Authorization, "Bearer ${Define.authInfo.accessToken}")
                }
            }
        }
    }
}