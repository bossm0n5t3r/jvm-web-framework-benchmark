package me.bossm0n5t3r.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import me.bossm0n5t3r.table.UserTable
import java.time.LocalDateTime

/**
 * JPA Entity for Spring MVC
 */
@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long = 0,
    @Column(nullable = false)
    override val name: String,
    @Column(nullable = false)
    override val email: String,
    @Column(name = "created_at")
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "updated_at")
    override val updatedAt: LocalDateTime = LocalDateTime.now(),
) : UserTable
