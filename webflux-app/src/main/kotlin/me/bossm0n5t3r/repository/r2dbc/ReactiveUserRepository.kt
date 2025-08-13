package me.bossm0n5t3r.repository.r2dbc

import me.bossm0n5t3r.entity.ReactiveUser
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * R2DBC Repository for Spring WebFlux
 */
@Repository
interface ReactiveUserRepository : ReactiveCrudRepository<ReactiveUser, Long> {
    fun findByEmail(email: String): Mono<ReactiveUser>

    fun findByNameContaining(name: String): Flux<ReactiveUser>

    @Query("SELECT * FROM users ORDER BY created_at DESC")
    fun findAllOrderByCreatedAtDesc(): Flux<ReactiveUser>
}
