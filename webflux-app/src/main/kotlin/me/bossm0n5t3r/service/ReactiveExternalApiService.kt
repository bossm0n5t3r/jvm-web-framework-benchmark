package me.bossm0n5t3r.service

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.reactive.awaitSingle
import me.bossm0n5t3r.entity.ReactiveExternalApiResponse
import me.bossm0n5t3r.repository.ReactiveExternalApiResponseRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange
import java.time.LocalDateTime

/**
 * Reactive service for calling external APIs and storing responses in database using coroutines
 */
@Service
class ReactiveExternalApiService(
    private val webClient: WebClient,
    private val objectMapper: ObjectMapper,
    private val externalApiResponseRepository: ReactiveExternalApiResponseRepository,
    @param:Value("\${external.api.base-url:http://localhost:8082}")
    private val externalApiBaseUrl: String,
) {
    /**
     * Call external API and store response in database using coroutines
     */
    suspend fun callExternalApiAndStore(
        endpoint: String,
        method: HttpMethod = HttpMethod.GET,
    ): ReactiveExternalApiResponse {
        val requestTimestamp = LocalDateTime.now()
        val fullUrl = "$externalApiBaseUrl$endpoint"

        return try {
            // Make HTTP call using WebClient with coroutines
            val response =
                when (method) {
                    HttpMethod.GET -> {
                        webClient
                            .get()
                            .uri(fullUrl)
                            .awaitExchange { clientResponse ->
                                val responseBody = clientResponse.awaitBody<String>()
                                Triple(responseBody, clientResponse.statusCode().value(), LocalDateTime.now())
                            }
                    }
                    HttpMethod.POST -> {
                        webClient
                            .post()
                            .uri(fullUrl)
                            .awaitExchange { clientResponse ->
                                val responseBody = clientResponse.awaitBody<String>()
                                Triple(responseBody, clientResponse.statusCode().value(), LocalDateTime.now())
                            }
                    }
                    else -> throw IllegalArgumentException("Unsupported HTTP method: $method")
                }

            val (responseBody, statusCode, responseTimestamp) = response

            // Create and save external API response entity
            val apiResponse =
                ReactiveExternalApiResponse(
                    apiEndpoint = endpoint,
                    httpMethod = method.toString(),
                    responseBody = responseBody,
                    statusCode = statusCode,
                    requestTimestamp = requestTimestamp,
                    responseTimestamp = responseTimestamp,
                    createdAt = LocalDateTime.now(),
                )

            externalApiResponseRepository.save(apiResponse).awaitSingle()
        } catch (e: Exception) {
            // Save error response
            val errorResponse =
                ReactiveExternalApiResponse(
                    apiEndpoint = endpoint,
                    httpMethod = method.toString(),
                    responseBody =
                        objectMapper.writeValueAsString(
                            mapOf(
                                "error" to e.message,
                                "timestamp" to LocalDateTime.now().toString(),
                            ),
                        ),
                    statusCode = 500,
                    requestTimestamp = requestTimestamp,
                    responseTimestamp = LocalDateTime.now(),
                    createdAt = LocalDateTime.now(),
                )

            externalApiResponseRepository.save(errorResponse).awaitSingle()
        }
    }

    /**
     * Call health check API
     */
    suspend fun callHealthApi() = callExternalApiAndStore("/api/external/health")

    /**
     * Call user API with specific ID
     */
    suspend fun callUserApi(userId: String) = callExternalApiAndStore("/api/external/user/$userId")

    /**
     * Call weather API with specific city
     */
    suspend fun callWeatherApi(city: String = "Seoul") = callExternalApiAndStore("/api/external/weather?city=$city")

    /**
     * Call stock API with specific symbol
     */
    suspend fun callStockApi(symbol: String) = callExternalApiAndStore("/api/external/stock/$symbol")

    /**
     * Call order API with specific order ID
     */
    suspend fun callOrderApi(orderId: String) = callExternalApiAndStore("/api/external/order/$orderId")

    /**
     * Call metrics API
     */
    suspend fun callMetricsApi() = callExternalApiAndStore("/api/external/metrics")
}
