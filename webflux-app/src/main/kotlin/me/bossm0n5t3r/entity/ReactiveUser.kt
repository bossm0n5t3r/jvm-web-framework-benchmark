package me.bossm0n5t3r.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

/**
 * R2DBC Entity for Spring WebFlux
 */
@Table("users")
data class ReactiveUser(
    @Id
    val id: Long = 0,
    @Column("name")
    val name: String,
    @Column("email")
    val email: String,
    @Column("created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column("updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)
