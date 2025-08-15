package me.bossm0n5t3r.service

import me.bossm0n5t3r.entity.ExternalApiResponse
import me.bossm0n5t3r.repository.ExternalApiResponseRepository
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import java.util.UUID
import kotlin.concurrent.atomics.ExperimentalAtomicApi

/**
 * Service for calling external APIs and storing responses in database
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
        val uuid = UUID.randomUUID().toString()
        val userInfo = callExternalApi("/api/external/user/$uuid")
        val weatherInfo = callExternalApi("/api/external/weather?city=${CITIES.random()}")
        val stockInfo = callExternalApi("/api/external/stock/${STOCK_SYMBOLS.random()}")
        val orderInfo = callExternalApi("/api/external/order/$uuid")
        val metricInfo = callExternalApi("/api/external/metrics")
        val externalApiResponse =
            ExternalApiResponse(
                userInfo = userInfo.orEmpty(),
                weatherInfo = weatherInfo.orEmpty(),
                stockPriceInfo = stockInfo.orEmpty(),
                orderStatusInfo = orderInfo.orEmpty(),
                metricInfo = metricInfo.orEmpty(),
            )
        return externalApiResponseRepository.save(externalApiResponse)
    }

    /**
     * Call external API using Virtual Threads
     */
    private fun callExternalApi(endpoint: String): String? =
        restClient
            .get()
            .uri("$EXTERNAL_API_BASE_URL$endpoint")
            .retrieve()
            .toEntity(String::class.java)
            .body
}
