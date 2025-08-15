package me.bossm0n5t3r.webflux

import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import me.bossm0n5t3r.repository.ReactiveExternalApiResponseRepository
import me.bossm0n5t3r.service.ReactiveExternalApiService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait

/**
 * External API endpoints using coroutines
 * Component responsible for handling external API interactions and storing the responses in a database.
 * Utilizes reactive service and repository to perform asynchronous operations.
 *
 * @property externalApiService Service handling external API calls.
 * @property externalApiResponseRepository Repository for storing external API responses.
 */
@Component
class ExternalHandler(
    private val externalApiService: ReactiveExternalApiService,
    private val externalApiResponseRepository: ReactiveExternalApiResponseRepository,
) {
    /**
     * Call external health API and store response in database using coroutines
     */
    suspend fun callExternalHealthApi(request: ServerRequest): ServerResponse =
        try {
            val response = externalApiService.callHealthApi()
            ServerResponse
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(response)
                .awaitSingle()
        } catch (e: Exception) {
            ServerResponse.badRequest().build().awaitSingle()
        }

    /**
     * Call external user API and store response in database using coroutines
     */
    suspend fun callExternalUserApi(request: ServerRequest): ServerResponse {
        val userId = request.pathVariable("userId")
        return try {
            val response = externalApiService.callUserApi(userId)
            ServerResponse
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(response)
                .awaitSingle()
        } catch (e: Exception) {
            ServerResponse.badRequest().build().awaitSingle()
        }
    }

    /**
     * Call external weather API and store response in database using coroutines
     */
    suspend fun callExternalWeatherApi(request: ServerRequest): ServerResponse {
        val city = request.queryParam("city").orElse("Seoul")
        return try {
            val response = externalApiService.callWeatherApi(city)
            ServerResponse
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(response)
                .awaitSingle()
        } catch (e: Exception) {
            ServerResponse.badRequest().build().awaitSingle()
        }
    }

    /**
     * Call external stock API and store response in database using coroutines
     */
    suspend fun callExternalStockApi(request: ServerRequest): ServerResponse {
        val symbol = request.pathVariable("symbol")
        return try {
            val response = externalApiService.callStockApi(symbol)
            ServerResponse
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(response)
                .awaitSingle()
        } catch (e: Exception) {
            ServerResponse.badRequest().build().awaitSingle()
        }
    }

    /**
     * Call external order API and store response in database using coroutines
     */
    suspend fun callExternalOrderApi(request: ServerRequest): ServerResponse {
        val orderId = request.pathVariable("orderId")
        return try {
            val response = externalApiService.callOrderApi(orderId)
            ServerResponse
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(response)
                .awaitSingle()
        } catch (e: Exception) {
            ServerResponse.badRequest().build().awaitSingle()
        }
    }

    /**
     * Call external metrics API and store response in database using coroutines
     */
    suspend fun callExternalMetricsApi(request: ServerRequest): ServerResponse =
        try {
            val response = externalApiService.callMetricsApi()
            ServerResponse
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(response)
                .awaitSingle()
        } catch (e: Exception) {
            ServerResponse.badRequest().build().awaitSingle()
        }

    /**
     * Get all stored external API responses
     */
    suspend fun getAllExternalApiResponses(request: ServerRequest): ServerResponse =
        ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyAndAwait(externalApiResponseRepository.findAllOrderByCreatedAtDesc().asFlow())

    /**
     * Get external API responses by endpoint
     */
    suspend fun getExternalApiResponsesByEndpoint(request: ServerRequest): ServerResponse {
        val endpoint = request.queryParam("endpoint").orElse("")
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyAndAwait(externalApiResponseRepository.findByApiEndpointOrderByCreatedAtDesc(endpoint).asFlow())
    }
}
