package me.bossm0n5t3r.webflux

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.withContext
import me.bossm0n5t3r.dto.ExternalApiResponseDto
import me.bossm0n5t3r.dto.toDto
import me.bossm0n5t3r.entity.ReactiveExternalApiResponse
import me.bossm0n5t3r.repository.ReactiveExternalApiResponseRepository
import me.bossm0n5t3r.util.Constants.CONNECT_TIMEOUT_MILLIS
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import java.time.Duration
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

    suspend fun callExternalApiWithDatabase(): ExternalApiResponseDto = callExternalApi().saveDatabase().toDto()

    /**
     * Call external API using coroutines
     */
    private suspend fun callExternalApi(endpoint: String): String = callExternalApiAndReturnMono(endpoint).awaitSingle()

    private fun callExternalApiAndReturnMono(endpoint: String): Mono<String> =
        webClient
            .get()
            .uri("${EXTERNAL_API_BASE_URL}$endpoint")
            .retrieve()
            .onStatus({ it.isError }) { resp ->
                // 에러 바디를 읽어서 예외 메시지에 포함(너무 길면 잘라내는 것도 추천)
                resp
                    .bodyToMono<String>()
                    .defaultIfEmpty("")
                    .flatMap { body ->
                        Mono.error(
                            Exception(
                                "External API call failed: status=${resp.statusCode().value()}, endpoint=$endpoint, body=$body",
                            ),
                        )
                    }
            }.bodyToMono<String>()
            .timeout(Duration.ofMillis(CONNECT_TIMEOUT_MILLIS))

    private suspend fun callExternalApi(): ReactiveExternalApiResponse =
        withContext(Dispatchers.IO) {
            coroutineScope {
                val uuid = UUID.randomUUID().toString()
                val userInfo = async { callExternalApi("/api/external/user/$uuid") }
                val weatherInfo = async { callExternalApi("/api/external/weather?city=${CITIES.random()}") }
                val stockInfo = async { callExternalApi("/api/external/stock/${STOCK_SYMBOLS.random()}") }
                val orderInfo = async { callExternalApi("/api/external/order/$uuid") }
                val metricInfo = async { callExternalApi("/api/external/metrics") }
                ReactiveExternalApiResponse(
                    userInfo = userInfo.await(),
                    weatherInfo = weatherInfo.await(),
                    stockPriceInfo = stockInfo.await(),
                    orderStatusInfo = orderInfo.await(),
                    metricInfo = metricInfo.await(),
                )
            }
        }

    suspend fun callExternalApiWithNoDatabase(): ExternalApiResponseDto = callExternalApi().toDto()

    private suspend fun ReactiveExternalApiResponse.saveDatabase() = externalApiResponseRepository.save(this).awaitSingle()

    fun callExternalApiWithNoDatabaseAndNoCoroutines(): Mono<ExternalApiResponseDto> {
        val uuid = UUID.randomUUID().toString()
        val userInfoMono = callExternalApiAndReturnMono("/api/external/user/$uuid")
        val weatherInfoMono = callExternalApiAndReturnMono("/api/external/weather?city=${CITIES.random()}")
        val stockInfoMono = callExternalApiAndReturnMono("/api/external/stock/${STOCK_SYMBOLS.random()}")
        val orderInfoMono = callExternalApiAndReturnMono("/api/external/order/$uuid")
        val metricInfoMono = callExternalApiAndReturnMono("/api/external/metrics")
        return Mono
            .zip(
                userInfoMono,
                weatherInfoMono,
                stockInfoMono,
                orderInfoMono,
                metricInfoMono,
            ).map { tuple ->
                ExternalApiResponseDto(
                    userInfo = tuple.t1,
                    weatherInfo = tuple.t2,
                    stockPriceInfo = tuple.t3,
                    orderStatusInfo = tuple.t4,
                    metricInfo = tuple.t5,
                )
            }
    }
}
