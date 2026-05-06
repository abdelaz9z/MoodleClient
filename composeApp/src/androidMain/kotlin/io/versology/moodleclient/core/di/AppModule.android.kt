package io.versology.moodleclient.core.di

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import io.versology.moodleclient.MoodleApplication
import kotlinx.serialization.json.Json

actual fun createPlatformHttpClient(): HttpClient {
    val context = MoodleApplication.instance

    val chuckerCollector = ChuckerCollector(
        context = context,
        showNotification = true,
        retentionPeriod = RetentionManager.Period.ONE_HOUR,
    )

    val chuckerInterceptor = ChuckerInterceptor.Builder(context)
        .collector(chuckerCollector)
        .maxContentLength(250_000L)
        .redactHeaders("Authorization", "Cookie")
        .alwaysReadResponseBody(true)
        .createShortcut(true)
        .build()

    return HttpClient(OkHttp) {
        engine {
            addInterceptor(chuckerInterceptor)
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                coerceInputValues = true
            })
        }
        install(Logging) {
            level = LogLevel.BODY
        }
    }
}
