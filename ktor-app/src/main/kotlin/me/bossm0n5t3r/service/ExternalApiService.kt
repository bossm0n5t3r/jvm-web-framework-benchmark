package me.bossm0n5t3r.service

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.timeout
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.bossm0n5t3r.dto.ExternalApiResponseDto
import me.bossm0n5t3r.repository.ExternalApiResponseRepository
import me.bossm0n5t3r.util.Constants
import java.util.UUID

class ExternalApiService(
    private val externalApiResponseRepository: ExternalApiResponseRepository,
) {
    companion object {
        private const val EXTERNAL_API_BASE_URL = "http://localhost:8082"
        private val CITIES = listOf("Seoul", "London", "New York", "Tokyo")
        private val STOCK_SYMBOLS = listOf("AAPL", "GOOGL", "TSLA", "MSFT")
    }

    private val httpClient =
        HttpClient(CIO) {
            install(HttpTimeout) {
                connectTimeoutMillis = Constants.CONNECT_TIMEOUT_MILLIS
                requestTimeoutMillis = Constants.RESPONSE_TIMEOUT_MILLIS
            }
        }

    suspend fun callHealthApi() {
        callExternalApi("/api/external/health")
    }

    suspend fun callExternalApiWithDatabase() = externalApiResponseRepository.save(callExternalApiWithNoDatabase())

    suspend fun callExternalApiWithNoDatabase() =
        withContext(Dispatchers.IO) {
            val uuid = UUID.randomUUID().toString()
            val userInfo = callExternalApi("/api/external/user/$uuid")
            val weatherInfo = callExternalApi("/api/external/weather?city=${CITIES.random()}")
            val stockInfo = callExternalApi("/api/external/stock/${STOCK_SYMBOLS.random()}")
            val orderInfo = callExternalApi("/api/external/order/$uuid")
            val metricInfo = callExternalApi("/api/external/metrics")

            ExternalApiResponseDto(
                userInfo = userInfo,
                weatherInfo = weatherInfo,
                stockPriceInfo = stockInfo,
                orderStatusInfo = orderInfo,
                metricInfo = metricInfo,
            )
        }

    private suspend fun callExternalApi(endpoint: String): String =
        httpClient
            .get("$EXTERNAL_API_BASE_URL$endpoint") {
                timeout {
                    connectTimeoutMillis = Constants.CONNECT_TIMEOUT_MILLIS
                    requestTimeoutMillis = Constants.RESPONSE_TIMEOUT_MILLIS
                }
            }.bodyAsText()
}
