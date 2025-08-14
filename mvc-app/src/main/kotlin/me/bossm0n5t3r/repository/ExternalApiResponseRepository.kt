package me.bossm0n5t3r.repository

import me.bossm0n5t3r.entity.ExternalApiResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

/**
 * JPA Repository for ExternalApiResponse entity
 */
@Repository
interface ExternalApiResponseRepository : JpaRepository<ExternalApiResponse, Long> {
    /**
     * Find all responses ordered by created date descending
     */
    @Query("SELECT e FROM ExternalApiResponse e ORDER BY e.createdAt DESC")
    fun findAllOrderByCreatedAtDesc(): List<ExternalApiResponse>

    /**
     * Find responses by API endpoint
     */
    fun findByApiEndpointOrderByCreatedAtDesc(apiEndpoint: String): List<ExternalApiResponse>

    /**
     * Find responses by HTTP method
     */
    fun findByHttpMethodOrderByCreatedAtDesc(httpMethod: String): List<ExternalApiResponse>

    /**
     * Find responses by status code
     */
    fun findByStatusCodeOrderByCreatedAtDesc(statusCode: Int): List<ExternalApiResponse>
}
