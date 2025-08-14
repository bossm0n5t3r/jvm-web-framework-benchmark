package me.bossm0n5t3r.repository

import me.bossm0n5t3r.entity.ReactiveExternalApiResponse
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

/**
 * R2DBC Repository for ReactiveExternalApiResponse entity
 */
@Repository
interface ReactiveExternalApiResponseRepository : ReactiveCrudRepository<ReactiveExternalApiResponse, Long> {
    /**
     * Find all responses ordered by created date descending
     */
    @Query("SELECT * FROM external_api_responses ORDER BY created_at DESC")
    fun findAllOrderByCreatedAtDesc(): Flux<ReactiveExternalApiResponse>

    /**
     * Find responses by API endpoint
     */
    fun findByApiEndpointOrderByCreatedAtDesc(apiEndpoint: String): Flux<ReactiveExternalApiResponse>

    /**
     * Find responses by HTTP method
     */
    fun findByHttpMethodOrderByCreatedAtDesc(httpMethod: String): Flux<ReactiveExternalApiResponse>

    /**
     * Find responses by status code
     */
    fun findByStatusCodeOrderByCreatedAtDesc(statusCode: Int): Flux<ReactiveExternalApiResponse>
}
