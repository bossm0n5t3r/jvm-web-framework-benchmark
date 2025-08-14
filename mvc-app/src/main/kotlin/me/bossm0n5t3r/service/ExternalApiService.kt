package me.bossm0n5t3r.service

import com.fasterxml.jackson.databind.ObjectMapper
import me.bossm0n5t3r.entity.ExternalApiResponse
import me.bossm0n5t3r.repository.ExternalApiResponseRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import java.time.LocalDateTime

/**
 * Service for calling external APIs and storing responses in database
 */
@Service
class ExternalApiService(
    private val restClient: RestClient,
    private val objectMapper: ObjectMapper,
    private val externalApiResponseRepository: ExternalApiResponseRepository,
    @Value("\${external.api.base-url:http://localhost:8082}")
    private val externalApiBaseUrl: String,
) {
    /**
     * Call external API and store response in database using Virtual Threads
     */
    fun callExternalApiAndStore(
        endpoint: String,
        method: HttpMethod = HttpMethod.GET,
    ): ExternalApiResponse {
        val requestTimestamp = LocalDateTime.now()
        val fullUrl = "$externalApiBaseUrl$endpoint"

        return try {
            // Make HTTP call - this will run on Virtual Thread
            val response: ResponseEntity<String> =
                when (method) {
                    HttpMethod.GET ->
                        restClient
                            .get()
                            .uri(fullUrl)
                            .retrieve()
                            .toEntity(String::class.java)
                    HttpMethod.POST ->
                        restClient
                            .post()
                            .uri(fullUrl)
                            .retrieve()
                            .toEntity(String::class.java)
                    else -> throw IllegalArgumentException("Unsupported HTTP method: $method")
                }

            val responseTimestamp = LocalDateTime.now()

            // Create and save external API response entity
            val apiResponse =
                ExternalApiResponse(
                    apiEndpoint = endpoint,
                    httpMethod = method.toString(),
                    responseBody = response.body ?: "",
                    statusCode = response.statusCode.value(),
                    requestTimestamp = requestTimestamp,
                    responseTimestamp = responseTimestamp,
                    createdAt = LocalDateTime.now(),
                )

            externalApiResponseRepository.save(apiResponse)
        } catch (e: Exception) {
            // Save error response
            val errorResponse =
                ExternalApiResponse(
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

            externalApiResponseRepository.save(errorResponse)
        }
    }

    /**
     * Call health check API
     */
    fun callHealthApi() = callExternalApiAndStore("/api/external/health")

    /**
     * Call user API with specific ID
     */
    fun callUserApi(userId: String) = callExternalApiAndStore("/api/external/user/$userId")

    /**
     * Call weather API with specific city
     */
    fun callWeatherApi(city: String = "Seoul") = callExternalApiAndStore("/api/external/weather?city=$city")

    /**
     * Call stock API with specific symbol
     */
    fun callStockApi(symbol: String) = callExternalApiAndStore("/api/external/stock/$symbol")

    /**
     * Call order API with specific order ID
     */
    fun callOrderApi(orderId: String) = callExternalApiAndStore("/api/external/order/$orderId")

    /**
     * Call metrics API
     */
    fun callMetricsApi() = callExternalApiAndStore("/api/external/metrics")
}
