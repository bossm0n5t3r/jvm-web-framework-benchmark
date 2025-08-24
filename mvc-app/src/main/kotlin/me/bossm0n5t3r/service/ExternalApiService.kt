package me.bossm0n5t3r.service

import me.bossm0n5t3r.entity.ExternalApiResponse
import me.bossm0n5t3r.repository.ExternalApiResponseRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.task.AsyncTaskExecutor
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import java.util.UUID
import kotlin.concurrent.atomics.ExperimentalAtomicApi
import org.springframework.web.client.RestClientException

/**
 * Service for calling external APIs and storing responses in a database
 */
@OptIn(ExperimentalAtomicApi::class)
@Service
class ExternalApiService(
    private val restClient: RestClient,
    private val externalApiResponseRepository: ExternalApiResponseRepository,
    @param:Qualifier("virtualThreadExecutor") private val taskExecutor: AsyncTaskExecutor,
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
                .toEntity(String::class.java)
                .body
        } catch (e: RestClientException) {
            println("Failed to call external API at $endpoint: ${e.message}")
            null
        }

    fun callExternalApiWithNoDatabase(): ExternalApiResponse {
        val uuid = UUID.randomUUID().toString()
        val userInfoFuture = taskExecutor.submit<String?> { callExternalApi("/api/external/user/$uuid") }
        val weatherInfoFuture =
            taskExecutor.submit<String?> { callExternalApi("/api/external/weather?city=${CITIES.random()}") }
        val stockInfoFuture =
            taskExecutor.submit<String?> { callExternalApi("/api/external/stock/${STOCK_SYMBOLS.random()}") }
        val orderInfoFuture = taskExecutor.submit<String?> { callExternalApi("/api/external/order/$uuid") }
        val metricInfoFuture = taskExecutor.submit<String?> { callExternalApi("/api/external/metrics") }

        return ExternalApiResponse(
            userInfo = userInfoFuture.get().orEmpty(),
            weatherInfo = weatherInfoFuture.get().orEmpty(),
            stockPriceInfo = stockInfoFuture.get().orEmpty(),
            orderStatusInfo = orderInfoFuture.get().orEmpty(),
            metricInfo = metricInfoFuture.get().orEmpty(),
        )
    }
}
