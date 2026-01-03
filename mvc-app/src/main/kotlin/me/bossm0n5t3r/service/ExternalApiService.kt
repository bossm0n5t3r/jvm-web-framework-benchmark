package me.bossm0n5t3r.service

import me.bossm0n5t3r.entity.ExternalApiResponse
import me.bossm0n5t3r.repository.ExternalApiResponseRepository
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestClientException
import org.springframework.web.client.toEntity
import java.util.UUID
import kotlin.concurrent.atomics.ExperimentalAtomicApi

/**
 * Service for calling external APIs and storing responses in a database
 */
@OptIn(ExperimentalAtomicApi::class)
@Service
class ExternalApiService(
    private val restClient: RestClient,
    private val externalApiResponseRepository: ExternalApiResponseRepository,
) {
    companion object {
        private const val EXTERNAL_API_BASE_URL = "http://localhost:8082"
        private val CITIES = listOf("Seoul", "London", "New York", "Tokyo")
        private val STOCK_SYMBOLS = listOf("AAPL", "GOOGL", "TSLA", "MSFT")
    }

    /**
     * Call health check API
     */
    fun callHealthApi() {
        println("Calling health check API")
    }

    fun callExternalApi(): ExternalApiResponse {
        val externalApiResponse = callExternalApiWithNoDatabase()
        return externalApiResponseRepository.save(externalApiResponse)
    }

    /**
     * Call external API using Virtual Threads
     */
    private fun callExternalApi(endpoint: String): String? =
        try {
            restClient
                .get()
                .uri("$EXTERNAL_API_BASE_URL$endpoint")
                .retrieve()
                .toEntity<String>()
                .body
        } catch (e: RestClientException) {
            println("Failed to call external API at $endpoint: ${e.message}")
            null
        }

    fun callExternalApiWithNoDatabase(): ExternalApiResponse {
        val uuid = UUID.randomUUID().toString()
        val userInfo = callExternalApi("/api/external/user/$uuid") ?: error("Not found userInfo")
        val weatherInfo = callExternalApi("/api/external/weather?city=${CITIES.random()}") ?: error("Not found weatherInfo")
        val stockInfo = callExternalApi("/api/external/stock/${STOCK_SYMBOLS.random()}") ?: error("Not found stockPriceInfo")
        val orderInfo = callExternalApi("/api/external/order/$uuid") ?: error("Not found orderStatusInfo")
        val metricInfo = callExternalApi("/api/external/metrics") ?: error("Not found metricInfo")

        return ExternalApiResponse(
            userInfo = userInfo,
            weatherInfo = weatherInfo,
            stockPriceInfo = stockInfo,
            orderStatusInfo = orderInfo,
            metricInfo = metricInfo,
        )
    }
}
