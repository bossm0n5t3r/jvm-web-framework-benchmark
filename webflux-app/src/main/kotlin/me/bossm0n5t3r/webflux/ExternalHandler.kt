package me.bossm0n5t3r.webflux

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import me.bossm0n5t3r.entity.ReactiveExternalApiResponse
import me.bossm0n5t3r.repository.ReactiveExternalApiResponseRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.toEntity
import org.springframework.web.reactive.function.server.ServerResponse
import java.time.LocalDateTime
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
    suspend fun callExternalHealthApi(): ServerResponse {
        println("Calling health check API")
        return ServerResponse.ok().build().awaitSingle()
    }

    suspend fun callExternalApi(): ServerResponse =
        coroutineScope {
            val uuid = UUID.randomUUID().toString()
            val userInfo = async { callExternalApi("/api/external/user/$uuid") }
            val weatherInfo = async { callExternalApi("/api/external/weather?city=${CITIES.random()}") }
            val stockInfo = async { callExternalApi("/api/external/stock/${STOCK_SYMBOLS.random()}") }
            val orderInfo = async { callExternalApi("/api/external/order/$uuid") }
            val metricInfo = async { callExternalApi("/api/external/metrics") }
            val externalApiResponse =
                ReactiveExternalApiResponse(
                    userInfo = userInfo.await().orEmpty(),
                    weatherInfo = weatherInfo.await().orEmpty(),
                    stockPriceInfo = stockInfo.await().orEmpty(),
                    orderStatusInfo = orderInfo.await().orEmpty(),
                    metricInfo = metricInfo.await().orEmpty(),
                    createdAt = LocalDateTime.now(),
                )
            val response = externalApiResponseRepository.save(externalApiResponse).awaitSingle()
            ServerResponse
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(response)
                .awaitSingle()
        }

    /**
     * Call external API using coroutines
     */
    suspend fun callExternalApi(endpoint: String): String? =
        webClient
            .get()
            .uri("${EXTERNAL_API_BASE_URL}$endpoint")
            .retrieve()
            .toEntity<String>()
            .awaitSingleOrNull()
            ?.body
}
