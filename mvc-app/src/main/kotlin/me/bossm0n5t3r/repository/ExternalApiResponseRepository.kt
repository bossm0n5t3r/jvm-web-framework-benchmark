package me.bossm0n5t3r.repository

import me.bossm0n5t3r.entity.ExternalApiResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * JPA Repository for ExternalApiResponse entity
 */
@Repository
interface ExternalApiResponseRepository : JpaRepository<ExternalApiResponse, Long>
