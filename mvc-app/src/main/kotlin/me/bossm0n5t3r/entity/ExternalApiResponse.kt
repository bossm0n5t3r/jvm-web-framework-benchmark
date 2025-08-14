package me.bossm0n5t3r.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.Table
import me.bossm0n5t3r.table.ExternalApiResponseTable
import java.time.LocalDateTime

/**
 * JPA Entity for storing external API responses
 */
@Entity
@Table(name = "external_api_responses")
data class ExternalApiResponse(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long = 0,
    @Lob
    @Column(nullable = false)
    override val userInfo: String,
    @Lob
    @Column(nullable = false)
    override val weatherInfo: String,
    @Lob
    @Column(nullable = false)
    override val stockPriceInfo: String,
    @Lob
    @Column(nullable = false)
    override val orderStatusInfo: String,
    @Lob
    @Column(nullable = false)
    override val metricInfo: String,
    @Column(name = "created_at")
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "updated_at")
    override val updatedAt: LocalDateTime = LocalDateTime.now(),
) : ExternalApiResponseTable
