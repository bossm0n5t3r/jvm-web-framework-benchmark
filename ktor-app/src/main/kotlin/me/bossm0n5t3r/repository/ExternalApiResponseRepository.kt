package me.bossm0n5t3r.repository

import me.bossm0n5t3r.dto.ExternalApiResponseDto
import me.bossm0n5t3r.model.ExternalApiResponseEntity
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction
import java.time.LocalDateTime

class ExternalApiResponseRepository(
    private val database: Database,
) {
    suspend fun save(dto: ExternalApiResponseDto): ExternalApiResponseEntity =
        suspendTransaction(database) {
            ExternalApiResponseEntity.new {
                this.userInfo = dto.userInfo
                this.weatherInfo = dto.weatherInfo
                this.stockPriceInfo = dto.stockPriceInfo
                this.orderStatusInfo = dto.orderStatusInfo
                this.metricInfo = dto.metricInfo
                this.createdAt = LocalDateTime.now()
                this.updatedAt = LocalDateTime.now()
            }
        }
}
