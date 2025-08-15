package me.bossm0n5t3r.repository

import me.bossm0n5t3r.entity.ReactiveExternalApiResponse
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

/**
 * R2DBC Repository for ReactiveExternalApiResponse entity
 */
@Repository
interface ReactiveExternalApiResponseRepository : ReactiveCrudRepository<ReactiveExternalApiResponse, Long>
