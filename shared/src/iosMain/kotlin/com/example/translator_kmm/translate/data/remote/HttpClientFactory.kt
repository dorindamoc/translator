package com.example.translator_kmm.translate.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import io.ktor.client.engine.darwin.*



actual class HttpClientFactory {
    actual fun create(): HttpClient = HttpClient(Darwin) {
        install(ContentNegotiation) { json() }
    }
}