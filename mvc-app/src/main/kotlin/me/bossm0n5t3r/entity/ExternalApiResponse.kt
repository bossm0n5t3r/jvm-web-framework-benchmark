package me.bossm0n5t3r.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.Table
import java.time.LocalDateTime

/**
 * JPA Entity for storing external API responses
 */
@Entity
@Table(name = "external_api_responses")
data class ExternalApiResponse(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(nullable = false)
    val apiEndpoint: String,
    @Column(nullable = false)
    val httpMethod: String,
    @Lob
    @Column(nullable = false)
    val responseBody: String,
    @Column(nullable = false)
    val statusCode: Int,
    @Column(name = "request_timestamp")
    val requestTimestamp: LocalDateTime = LocalDateTime.now(),
    @Column(name = "response_timestamp")
    val responseTimestamp: LocalDateTime = LocalDateTime.now(),
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
)
