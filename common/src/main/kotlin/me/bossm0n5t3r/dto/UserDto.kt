package me.bossm0n5t3r.dto

import me.bossm0n5t3r.table.UserTable
import java.time.LocalDateTime

data class UserDto(
    val id: Long,
    val name: String,
    val email: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)

fun UserTable.toDto() =
    UserDto(
        id = this.notNullId,
        name = this.name,
        email = this.email,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
    )
