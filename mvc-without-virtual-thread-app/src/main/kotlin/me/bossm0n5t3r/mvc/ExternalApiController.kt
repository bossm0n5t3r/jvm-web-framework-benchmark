package me.bossm0n5t3r.mvc

import me.bossm0n5t3r.entity.ExternalApiResponse
import me.bossm0n5t3r.service.ExternalApiService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Controller responsible for handling external API interactions and storing the responses.
 *
 * This controller provides endpoints for calling external APIs using virtual threads
 * and managing the persisted responses in the database.
 * It facilitates integration with external systems and provides methods for retrieving the results.
 */
@RestController
@RequestMapping("/mvc/external")
class ExternalApiController(
    private val externalApiService: ExternalApiService,
) {
    /**
     * Call external health API using Virtual Threads
     */
    @GetMapping("/health")
    fun callExternalHealthApi() {
        externalApiService.callHealthApi()
    }

    @PostMapping
    fun callExternalApi(): ExternalApiResponse = externalApiService.callExternalApi()

    @GetMapping("/no-db")
    fun callExternalApiWithNoDatabase(): ExternalApiResponse = externalApiService.callExternalApiWithNoDatabase()
}
