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
    val id: Long = 0,
    @Column("user_info")
    override var userInfo: String,
    @Column("weather_info")
    override var weatherInfo: String,
    @Column("stock_price_info")
    override var stockPriceInfo: String,
    @Column("order_status_info")
    override var orderStatusInfo: String,
    @Column("metric_info")
    override var metricInfo: String,
    @Column("created_at")
    override var createdAt: LocalDateTime = LocalDateTime.now(),
    @Column("updated_at")
    override var updatedAt: LocalDateTime = LocalDateTime.now(),
) : ExternalApiResponseTable {
    override val notNullId: Long = id
}
