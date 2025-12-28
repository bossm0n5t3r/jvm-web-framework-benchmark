package me.bossm0n5t3r.dto

import me.bossm0n5t3r.table.UserTable

data class UserDto(
    val name: String,
    val email: String,
)

fun UserTable.toDto() =
    UserDto(
        name = this.name,
        email = this.email,
    )
