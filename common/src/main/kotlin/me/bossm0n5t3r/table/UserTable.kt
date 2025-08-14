package me.bossm0n5t3r.table

import java.time.LocalDateTime

interface UserTable {
    val id: Long
    val name: String
    val email: String
    val createdAt: LocalDateTime
    val updatedAt: LocalDateTime
}
