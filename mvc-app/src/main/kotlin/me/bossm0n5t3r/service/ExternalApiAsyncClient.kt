package me.bossm0n5t3r.service

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestClientException
import org.springframework.web.client.toEntity
import java.util.concurrent.CompletableFuture

@Component
class ExternalApiAsyncClient(
    private val restClient: RestClient,
) {
    @Async("virtualThreadExecutor")
    fun callExternalApi(endpoint: String): CompletableFuture<String?> =
        CompletableFuture.completedFuture(
            try {
                restClient
                    .get()
                    .uri(endpoint)
                    .retrieve()
                    .toEntity<String>()
                    .body
            } catch (e: RestClientException) {
                println("Failed to call external API at $endpoint: ${e.message}")
                null
            },
        )
}
