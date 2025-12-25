package me.bossm0n5t3r.table

import java.time.LocalDateTime

interface UserTable {
    val notNullId: Long
    var name: String
    var email: String
    var createdAt: LocalDateTime
    var updatedAt: LocalDateTime
}
