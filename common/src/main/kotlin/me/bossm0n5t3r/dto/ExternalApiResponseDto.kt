package me.bossm0n5t3r.dto

import me.bossm0n5t3r.table.ExternalApiResponseTable
import java.time.LocalDateTime

data class ExternalApiResponseDto(
    val id: Long,
    val userInfo: String,
    val weatherInfo: String,
    val stockPriceInfo: String,
    val orderStatusInfo: String,
    val metricInfo: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)

fun ExternalApiResponseTable.toDto() =
    ExternalApiResponseDto(
        id = this.notNullId,
        userInfo = this.userInfo,
        weatherInfo = this.weatherInfo,
        stockPriceInfo = this.stockPriceInfo,
        orderStatusInfo = this.orderStatusInfo,
        metricInfo = this.metricInfo,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
    )
