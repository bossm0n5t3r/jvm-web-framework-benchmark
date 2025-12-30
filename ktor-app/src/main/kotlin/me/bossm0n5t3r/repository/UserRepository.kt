package me.bossm0n5t3r.repository

import me.bossm0n5t3r.dto.UserRequest
import me.bossm0n5t3r.model.UserEntity
import me.bossm0n5t3r.model.Users
import org.jetbrains.exposed.v1.core.SortOrder
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.core.like
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction
import java.time.LocalDateTime

class UserRepository(
    private val database: Database,
) {
    suspend fun findAllOrderByCreatedAtDesc(): List<UserEntity> =
        suspendTransaction(database) {
            UserEntity.all().orderBy(Users.createdAt to SortOrder.DESC).toList()
        }

    suspend fun findById(id: Long): UserEntity? =
        suspendTransaction(database) {
            UserEntity.findById(id)
        }

    suspend fun findByEmail(email: String): UserEntity? =
        suspendTransaction(database) {
            UserEntity.find { Users.email eq email }.firstOrNull()
        }

    suspend fun findByNameContaining(name: String): List<UserEntity> =
        suspendTransaction(database) {
            UserEntity.find { Users.name like "%$name%" }.toList()
        }

    suspend fun create(userRequest: UserRequest): UserEntity =
        suspendTransaction(database) {
            UserEntity.new {
                name = userRequest.name
                email = userRequest.email
                createdAt = LocalDateTime.now()
                updatedAt = LocalDateTime.now()
            }
        }

    suspend fun update(
        id: Long,
        userRequest: UserRequest,
    ): UserEntity? =
        suspendTransaction(database) {
            UserEntity.findById(id)?.apply {
                name = userRequest.name
                email = userRequest.email
                updatedAt = LocalDateTime.now()
            }
        }

    suspend fun deleteById(id: Long): Boolean =
        suspendTransaction(database) {
            UserEntity.findById(id)?.delete() != null
        }

    suspend fun deleteAll() =
        suspendTransaction(database) {
            UserEntity.all().forEach { it.delete() }
        }
}
