package me.bossm0n5t3r.model

import me.bossm0n5t3r.table.ExternalApiResponseTable
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.LongIdTable
import org.jetbrains.exposed.v1.dao.LongEntity
import org.jetbrains.exposed.v1.dao.LongEntityClass
import org.jetbrains.exposed.v1.javatime.datetime

object ExternalApiResponses : LongIdTable("external_api_responses") {
    val userInfo = text("user_info")
    val weatherInfo = text("weather_info")
    val stockPriceInfo = text("stock_price_info")
    val orderStatusInfo = text("order_status_info")
    val metricInfo = text("metric_info")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}

class ExternalApiResponseEntity(
    id: EntityID<Long>,
) : LongEntity(id),
    ExternalApiResponseTable {
    companion object : LongEntityClass<ExternalApiResponseEntity>(ExternalApiResponses)

    override var userInfo by ExternalApiResponses.userInfo
    override var weatherInfo by ExternalApiResponses.weatherInfo
    override var stockPriceInfo by ExternalApiResponses.stockPriceInfo
    override var orderStatusInfo by ExternalApiResponses.orderStatusInfo
    override var metricInfo by ExternalApiResponses.metricInfo
    override var createdAt by ExternalApiResponses.createdAt
    override var updatedAt by ExternalApiResponses.updatedAt

    override val notNullId: Long = id.value
}
