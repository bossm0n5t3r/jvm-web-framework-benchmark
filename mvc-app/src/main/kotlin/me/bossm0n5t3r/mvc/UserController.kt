package me.bossm0n5t3r.mvc

import me.bossm0n5t3r.dto.UserRequest
import me.bossm0n5t3r.entity.ExternalApiResponse
import me.bossm0n5t3r.entity.User
import me.bossm0n5t3r.repository.ExternalApiResponseRepository
import me.bossm0n5t3r.repository.UserRepository
import me.bossm0n5t3r.service.ExternalApiService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

/**
 * Spring MVC Controller for traditional blocking operations
 */
@RestController
@RequestMapping("/mvc/users")
class UserController(
    private val userRepository: UserRepository,
    private val externalApiService: ExternalApiService,
    private val externalApiResponseRepository: ExternalApiResponseRepository,
) {
    @GetMapping
    fun getAllUsers(): List<User> = userRepository.findAllOrderByCreatedAtDesc()

    @GetMapping("/{id}")
    fun getUserById(
        @PathVariable id: Long,
    ): ResponseEntity<User> {
        val user = userRepository.findById(id)
        return if (user.isPresent) {
            ResponseEntity.ok(user.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/search")
    fun searchUsersByName(
        @RequestParam name: String,
    ): List<User> = userRepository.findByNameContaining(name)

    @GetMapping("/email/{email}")
    fun getUserByEmail(
        @PathVariable email: String,
    ): ResponseEntity<User> {
        val user = userRepository.findByEmail(email)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createUser(
        @RequestBody userRequest: UserRequest,
    ): ResponseEntity<User> {
        val user =
            User(
                name = userRequest.name,
                email = userRequest.email,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
            )
        val savedUser = userRepository.save(user)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser)
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody userRequest: UserRequest,
    ): ResponseEntity<User> {
        val existingUser = userRepository.findById(id)
        return if (existingUser.isPresent) {
            val updatedUser =
                existingUser.get().copy(
                    name = userRequest.name,
                    email = userRequest.email,
                    updatedAt = LocalDateTime.now(),
                )
            val savedUser = userRepository.save(updatedUser)
            ResponseEntity.ok(savedUser)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUser(
        @PathVariable id: Long,
    ): ResponseEntity<Void> =
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }

    @DeleteMapping
    fun deleteAllUsers(): ResponseEntity<Void> {
        userRepository.deleteAll()
        return ResponseEntity.noContent().build()
    }

    // =================================
    // External API endpoints using Virtual Threads
    // =================================

    /**
     * Call external health API and store response in database using Virtual Threads
     */
    @PostMapping("/external/health")
    fun callExternalHealthApi(): ResponseEntity<ExternalApiResponse> {
        val response = externalApiService.callHealthApi()
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    /**
     * Call external user API and store response in database using Virtual Threads
     */
    @PostMapping("/external/user/{userId}")
    fun callExternalUserApi(
        @PathVariable userId: String,
    ): ResponseEntity<ExternalApiResponse> {
        val response = externalApiService.callUserApi(userId)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    /**
     * Call external weather API and store response in database using Virtual Threads
     */
    @PostMapping("/external/weather")
    fun callExternalWeatherApi(
        @RequestParam(defaultValue = "Seoul") city: String,
    ): ResponseEntity<ExternalApiResponse> {
        val response = externalApiService.callWeatherApi(city)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    /**
     * Call external stock API and store response in database using Virtual Threads
     */
    @PostMapping("/external/stock/{symbol}")
    fun callExternalStockApi(
        @PathVariable symbol: String,
    ): ResponseEntity<ExternalApiResponse> {
        val response = externalApiService.callStockApi(symbol)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    /**
     * Call external order API and store response in database using Virtual Threads
     */
    @PostMapping("/external/order/{orderId}")
    fun callExternalOrderApi(
        @PathVariable orderId: String,
    ): ResponseEntity<ExternalApiResponse> {
        val response = externalApiService.callOrderApi(orderId)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    /**
     * Call external metrics API and store response in database using Virtual Threads
     */
    @PostMapping("/external/metrics")
    fun callExternalMetricsApi(): ResponseEntity<ExternalApiResponse> {
        val response = externalApiService.callMetricsApi()
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    /**
     * Get all stored external API responses
     */
    @GetMapping("/external/responses")
    fun getAllExternalApiResponses(): List<ExternalApiResponse> = externalApiResponseRepository.findAllOrderByCreatedAtDesc()

    /**
     * Get external API responses by endpoint
     */
    @GetMapping("/external/responses/endpoint")
    fun getExternalApiResponsesByEndpoint(
        @RequestParam endpoint: String,
    ): List<ExternalApiResponse> = externalApiResponseRepository.findByApiEndpointOrderByCreatedAtDesc(endpoint)
}
