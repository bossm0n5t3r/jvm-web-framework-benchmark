package me.bossm0n5t3r.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

/**
 * R2DBC Entity for storing external API responses in Spring WebFlux
 */
@Table("external_api_responses")
data class ReactiveExternalApiResponse(
    @Id
    val id: Long = 0,
    @Column("api_endpoint")
    val apiEndpoint: String,
    @Column("http_method")
    val httpMethod: String,
    @Column("response_body")
    val responseBody: String,
    @Column("status_code")
    val statusCode: Int,
    @Column("request_timestamp")
    val requestTimestamp: LocalDateTime = LocalDateTime.now(),
    @Column("response_timestamp")
    val responseTimestamp: LocalDateTime = LocalDateTime.now(),
    @Column("created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
)
