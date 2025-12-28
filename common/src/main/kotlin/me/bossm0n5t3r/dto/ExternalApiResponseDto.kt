package me.bossm0n5t3r.dto

import me.bossm0n5t3r.table.ExternalApiResponseTable

data class ExternalApiResponseDto(
    val userInfo: String,
    val weatherInfo: String,
    val stockPriceInfo: String,
    val orderStatusInfo: String,
    val metricInfo: String,
)

fun ExternalApiResponseTable.toDto() =
    ExternalApiResponseDto(
        userInfo = this.userInfo,
        weatherInfo = this.weatherInfo,
        stockPriceInfo = this.stockPriceInfo,
        orderStatusInfo = this.orderStatusInfo,
        metricInfo = this.metricInfo,
    )
