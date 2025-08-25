package me.bossm0n5t3r.webflux

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Spring WebFlux Router Configuration
 */
@RestController
@RequestMapping("/webflux/external")
class ExternalRouter(
    private val externalHandler: ExternalHandler,
) {
    @GetMapping("/health")
    suspend fun health() = externalHandler.callExternalHealthApi()

    @PostMapping
    suspend fun callExternalApiWithDatabase() = externalHandler.callExternalApiWithDatabase()

    @GetMapping("/no-db")
    suspend fun callExternalApiWithNoDatabase() = externalHandler.callExternalApiWithNoDatabase()
}
