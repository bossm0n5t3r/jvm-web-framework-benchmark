package me.bossm0n5t3r.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import me.bossm0n5t3r.table.ExternalApiResponseTable
import java.time.LocalDateTime

/**
 * JPA Entity for storing external API responses
 */
@Entity
@Table(name = "external_api_responses")
open class ExternalApiResponse(
    @Column(nullable = false)
    override var userInfo: String,
    @Column(nullable = false)
    override var weatherInfo: String,
    @Column(nullable = false)
    override var stockPriceInfo: String,
    @Column(nullable = false)
    override var orderStatusInfo: String,
    @Column(nullable = false)
    override var metricInfo: String,
    @Column(name = "created_at")
    override var createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "updated_at")
    override var updatedAt: LocalDateTime = LocalDateTime.now(),
) : ExternalApiResponseTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    override val notNullId: Long
        get() = requireNotNull(id)
}
