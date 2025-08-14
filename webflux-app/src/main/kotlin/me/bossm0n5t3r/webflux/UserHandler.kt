package me.bossm0n5t3r.webflux

import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactive.awaitSingleOrNull
import me.bossm0n5t3r.dto.UserRequest
import me.bossm0n5t3r.entity.ReactiveUser
import me.bossm0n5t3r.repository.ReactiveExternalApiResponseRepository
import me.bossm0n5t3r.repository.ReactiveUserRepository
import me.bossm0n5t3r.service.ReactiveExternalApiService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyAndAwait
import java.time.LocalDateTime

/**
 * Spring WebFlux Handler for reactive operations
 */
@Component
class UserHandler(
    private val userRepository: ReactiveUserRepository,
    private val externalApiService: ReactiveExternalApiService,
    private val externalApiResponseRepository: ReactiveExternalApiResponseRepository,
) {
    suspend fun getAllUsers(request: ServerRequest): ServerResponse =
        ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyAndAwait(userRepository.findAllOrderByCreatedAtDesc().asFlow())

    suspend fun getUserById(request: ServerRequest): ServerResponse {
        val id =
            request.pathVariable("id").toLongOrNull()
                ?: return ServerResponse.badRequest().build().awaitSingle()

        return try {
            val user = userRepository.findById(id).awaitSingle()
            ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(user)
                .awaitSingle()
        } catch (e: Exception) {
            ServerResponse.notFound().build().awaitSingle()
        }
    }

    suspend fun searchUsersByName(request: ServerRequest): ServerResponse {
        val name = request.queryParam("name").orElse("")
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyAndAwait(userRepository.findByNameContaining(name).asFlow())
    }

    suspend fun getUserByEmail(request: ServerRequest): ServerResponse {
        val email = request.pathVariable("email")
        return try {
            val user = userRepository.findByEmail(email).awaitSingle()
            ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(user)
                .awaitSingle()
        } catch (e: Exception) {
            ServerResponse.notFound().build().awaitSingle()
        }
    }

    suspend fun createUser(request: ServerRequest): ServerResponse =
        try {
            val userRequest = request.awaitBody<UserRequest>()
            val user =
                ReactiveUser(
                    name = userRequest.name,
                    email = userRequest.email,
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.now(),
                )
            val savedUser = userRepository.save(user).awaitSingle()
            ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(savedUser)
                .awaitSingle()
        } catch (e: Exception) {
            ServerResponse.badRequest().build().awaitSingle()
        }

    suspend fun updateUser(request: ServerRequest): ServerResponse {
        val id =
            request.pathVariable("id").toLongOrNull()
                ?: return ServerResponse.badRequest().build().awaitSingle()

        return try {
            val userRequest = request.awaitBody<UserRequest>()
            val existingUser = userRepository.findById(id).awaitSingle()
            val updatedUser =
                existingUser.copy(
                    name = userRequest.name,
                    email = userRequest.email,
                    updatedAt = LocalDateTime.now(),
                )
            val savedUser = userRepository.save(updatedUser).awaitSingle()
            ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(savedUser)
                .awaitSingle()
        } catch (e: Exception) {
            // Handle both user not found and other errors
            if (e is NoSuchElementException) {
                ServerResponse.notFound().build().awaitSingle()
            } else {
                ServerResponse.badRequest().build().awaitSingle()
            }
        }
    }

    suspend fun deleteUser(request: ServerRequest): ServerResponse {
        val id =
            request.pathVariable("id").toLongOrNull()
                ?: return ServerResponse.badRequest().build().awaitSingle()

        return try {
            val exists = userRepository.existsById(id).awaitSingle()
            if (exists) {
                userRepository.deleteById(id).awaitSingle()
                ServerResponse.noContent().build().awaitSingle()
            } else {
                ServerResponse.notFound().build().awaitSingle()
            }
        } catch (e: Exception) {
            ServerResponse.badRequest().build().awaitSingle()
        }
    }

    suspend fun deleteAllUsers(request: ServerRequest): ServerResponse =
        try {
            userRepository.deleteAll().awaitSingle()
            ServerResponse.noContent().build().awaitSingle()
        } catch (e: Exception) {
            ServerResponse.badRequest().build().awaitSingle()
        }

    // =================================
    // External API endpoints using coroutines
    // =================================

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
