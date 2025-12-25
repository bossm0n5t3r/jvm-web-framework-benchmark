package me.bossm0n5t3r.table

import java.time.LocalDateTime

interface ExternalApiResponseTable {
    val notNullId: Long
    var userInfo: String
    var weatherInfo: String
    var stockPriceInfo: String
    var orderStatusInfo: String
    var metricInfo: String
    var createdAt: LocalDateTime
    var updatedAt: LocalDateTime
}
