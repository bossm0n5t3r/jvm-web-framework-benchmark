package me.bossm0n5t3r.entity

import me.bossm0n5t3r.table.ExternalApiResponseTable
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
    override val id: Long = 0,
    @Column("user_info")
    override val userInfo: String,
    @Column("weather_info")
    override val weatherInfo: String,
    @Column("stock_price_info")
    override val stockPriceInfo: String,
    @Column("order_status_info")
    override val orderStatusInfo: String,
    @Column("metric_info")
    override val metricInfo: String,
    @Column("created_at")
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column("updated_at")
    override val updatedAt: LocalDateTime = LocalDateTime.now(),
) : ExternalApiResponseTable
