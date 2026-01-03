package me.bossm0n5t3r.service

import me.bossm0n5t3r.entity.ExternalApiResponse
import me.bossm0n5t3r.repository.ExternalApiResponseRepository
import org.springframework.stereotype.Service
import java.util.UUID
import kotlin.concurrent.atomics.ExperimentalAtomicApi

/**
 * Service for calling external APIs and storing responses in a database
 */
@OptIn(ExperimentalAtomicApi::class)
@Service
class ExternalApiService(
    private val externalApiAsyncClient: ExternalApiAsyncClient,
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

    fun callExternalApiWithNoDatabase(): ExternalApiResponse {
        val uuid = UUID.randomUUID().toString()
        val userInfo = externalApiAsyncClient.callExternalApi("$EXTERNAL_API_BASE_URL/api/external/user/$uuid")
        val weatherInfo = externalApiAsyncClient.callExternalApi("$EXTERNAL_API_BASE_URL/api/external/weather?city=${CITIES.random()}")
        val stockInfo = externalApiAsyncClient.callExternalApi("$EXTERNAL_API_BASE_URL/api/external/stock/${STOCK_SYMBOLS.random()}")
        val orderInfo = externalApiAsyncClient.callExternalApi("$EXTERNAL_API_BASE_URL/api/external/order/$uuid")
        val metricInfo = externalApiAsyncClient.callExternalApi("$EXTERNAL_API_BASE_URL/api/external/metrics")

        return ExternalApiResponse(
            userInfo = userInfo.join() ?: error("Not found userInfo"),
            weatherInfo = weatherInfo.join() ?: error("Not found weatherInfo"),
            stockPriceInfo = stockInfo.join() ?: error("Not found stockPriceInfo"),
            orderStatusInfo = orderInfo.join() ?: error("Not found orderStatusInfo"),
            metricInfo = metricInfo.join() ?: error("Not found metricInfo"),
        )
    }
}
