package me.bossm0n5t3r.webflux

import me.bossm0n5t3r.dto.UserRequest
import me.bossm0n5t3r.entity.ReactiveUser
import me.bossm0n5t3r.repository.r2dbc.ReactiveUserRepository
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.time.LocalDateTime

/**
 * Spring WebFlux Handler for reactive operations
 */
@Component
class UserHandler(
    private val userRepository: ReactiveUserRepository,
) {
    fun getAllUsers(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(userRepository.findAllOrderByCreatedAtDesc(), ReactiveUser::class.java)

    fun getUserById(request: ServerRequest): Mono<ServerResponse> {
        val id =
            request.pathVariable("id").toLongOrNull()
                ?: return ServerResponse.badRequest().build()

        return userRepository
            .findById(id)
            .flatMap { user ->
                ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(user)
            }.switchIfEmpty(ServerResponse.notFound().build())
    }

    fun searchUsersByName(request: ServerRequest): Mono<ServerResponse> {
        val name = request.queryParam("name").orElse("")
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(userRepository.findByNameContaining(name), ReactiveUser::class.java)
    }

    fun getUserByEmail(request: ServerRequest): Mono<ServerResponse> {
        val email = request.pathVariable("email")
        return userRepository
            .findByEmail(email)
            .flatMap { user ->
                ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(user)
            }.switchIfEmpty(ServerResponse.notFound().build())
    }

    fun createUser(request: ServerRequest): Mono<ServerResponse> =
        request
            .bodyToMono(UserRequest::class.java)
            .flatMap { userRequest ->
                val user =
                    ReactiveUser(
                        name = userRequest.name,
                        email = userRequest.email,
                        createdAt = LocalDateTime.now(),
                        updatedAt = LocalDateTime.now(),
                    )
                userRepository.save(user)
            }.flatMap { savedUser ->
                ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(savedUser)
            }.onErrorResume {
                ServerResponse.badRequest().build()
            }

    fun updateUser(request: ServerRequest): Mono<ServerResponse> {
        val id =
            request.pathVariable("id").toLongOrNull()
                ?: return ServerResponse.badRequest().build()

        return request
            .bodyToMono(UserRequest::class.java)
            .flatMap { userRequest ->
                userRepository
                    .findById(id)
                    .flatMap { existingUser ->
                        val updatedUser =
                            existingUser.copy(
                                name = userRequest.name,
                                email = userRequest.email,
                                updatedAt = LocalDateTime.now(),
                            )
                        userRepository.save(updatedUser)
                    }.flatMap { savedUser ->
                        ServerResponse
                            .ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(savedUser)
                    }.switchIfEmpty(ServerResponse.notFound().build())
            }.onErrorResume {
                ServerResponse.badRequest().build()
            }
    }

    fun deleteUser(request: ServerRequest): Mono<ServerResponse> {
        val id =
            request.pathVariable("id").toLongOrNull()
                ?: return ServerResponse.badRequest().build()

        return userRepository
            .existsById(id)
            .flatMap { exists ->
                if (exists) {
                    userRepository
                        .deleteById(id)
                        .then(ServerResponse.noContent().build())
                } else {
                    ServerResponse.notFound().build()
                }
            }
    }
}
