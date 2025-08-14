package me.bossm0n5t3r.table

import java.time.LocalDateTime

interface ExternalApiResponseTable {
    val id: Long
    val userInfo: String
    val weatherInfo: String
    val stockPriceInfo: String
    val orderStatusInfo: String
    val metricInfo: String
    val createdAt: LocalDateTime
    val updatedAt: LocalDateTime
}
