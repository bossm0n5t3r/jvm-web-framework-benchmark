package me.bossm0n5t3r.webflux

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import me.bossm0n5t3r.entity.ReactiveExternalApiResponse
import me.bossm0n5t3r.repository.ReactiveExternalApiResponseRepository
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import java.util.UUID

@Component
class ExternalHandler(
    private val webClient: WebClient,
    private val externalApiResponseRepository: ReactiveExternalApiResponseRepository,
) {
    companion object {
        private const val EXTERNAL_API_BASE_URL = "http://localhost:8082"
        private val CITIES = listOf("Seoul", "London", "New York", "Tokyo")
        private val STOCK_SYMBOLS = listOf("AAPL", "GOOGL", "TSLA", "MSFT")
    }

    /**
     * Call external health API using coroutines
     */
    suspend fun callExternalHealthApi() {
        println("Calling health check API")
    }

    suspend fun callExternalApi(): ReactiveExternalApiResponse = callExternalApiWithNoDatabase().saveDatabase()

    /**
     * Call external API using coroutines
     */
    private suspend fun callExternalApi(endpoint: String): String? =
        try {
            webClient
                .get()
                .uri("${EXTERNAL_API_BASE_URL}$endpoint")
                .retrieve()
                .bodyToMono(String::class.java)
                .awaitSingleOrNull()
        } catch (e: Exception) {
            println("Failed to call external API at $endpoint: ${e.message}")
            null
        }

    suspend fun callExternalApiWithNoDatabase(): ReactiveExternalApiResponse =
        coroutineScope {
            val uuid = UUID.randomUUID().toString()
            val userInfo = async { callExternalApi("/api/external/user/$uuid") }
            val weatherInfo = async { callExternalApi("/api/external/weather?city=${CITIES.random()}") }
            val stockInfo = async { callExternalApi("/api/external/stock/${STOCK_SYMBOLS.random()}") }
            val orderInfo = async { callExternalApi("/api/external/order/$uuid") }
            val metricInfo = async { callExternalApi("/api/external/metrics") }
            ReactiveExternalApiResponse(
                userInfo = userInfo.await().orEmpty(),
                weatherInfo = weatherInfo.await().orEmpty(),
                stockPriceInfo = stockInfo.await().orEmpty(),
                orderStatusInfo = orderInfo.await().orEmpty(),
                metricInfo = metricInfo.await().orEmpty(),
            )
        }

    private suspend fun ReactiveExternalApiResponse.saveDatabase() = externalApiResponseRepository.save(this).awaitSingle()
}
