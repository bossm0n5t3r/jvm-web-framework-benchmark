package me.bossm0n5t3r.service

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.endpoint
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.timeout
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
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
            engine {
                maxConnectionsCount = 1000
                endpoint {
                    maxConnectionsPerRoute = 1000
                    pipelineMaxSize = 20
                    connectTimeout = Constants.CONNECT_TIMEOUT_MILLIS
                    requestTimeout = Constants.RESPONSE_TIMEOUT_MILLIS
                }
            }
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
            coroutineScope {
                val uuid = UUID.randomUUID().toString()
                val userInfo = async { callExternalApi("/api/external/user/$uuid") }
                val weatherInfo = async { callExternalApi("/api/external/weather", mapOf("city" to CITIES.random())) }
                val stockInfo = async { callExternalApi("/api/external/stock/${STOCK_SYMBOLS.random()}") }
                val orderInfo = async { callExternalApi("/api/external/order/$uuid") }
                val metricInfo = async { callExternalApi("/api/external/metrics") }

                ExternalApiResponseDto(
                    userInfo = userInfo.await(),
                    weatherInfo = weatherInfo.await(),
                    stockPriceInfo = stockInfo.await(),
                    orderStatusInfo = orderInfo.await(),
                    metricInfo = metricInfo.await(),
                )
            }
        }

    private suspend fun callExternalApi(
        endpoint: String,
        params: Map<String, String> = emptyMap(),
    ): String =
        httpClient
            .get("$EXTERNAL_API_BASE_URL$endpoint") {
                if (params.isNotEmpty()) {
                    url {
                        params.forEach { (key, value) ->
                            parameters.append(key, value)
                        }
                    }
                }
                timeout {
                    connectTimeoutMillis = Constants.CONNECT_TIMEOUT_MILLIS
                    requestTimeoutMillis = Constants.RESPONSE_TIMEOUT_MILLIS
                }
            }.bodyAsText()
}
