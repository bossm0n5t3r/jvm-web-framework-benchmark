package me.bossm0n5t3r.model

import me.bossm0n5t3r.table.UserTable
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.LongIdTable
import org.jetbrains.exposed.v1.dao.LongEntity
import org.jetbrains.exposed.v1.dao.LongEntityClass
import org.jetbrains.exposed.v1.javatime.datetime

object Users : LongIdTable("users") {
    val name = varchar("name", 50)
    val email = varchar("email", 100)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}

class UserEntity(
    id: EntityID<Long>,
) : LongEntity(id),
    UserTable {
    companion object : LongEntityClass<UserEntity>(Users)

    override var name by Users.name
    override var email by Users.email
    override var createdAt by Users.createdAt
    override var updatedAt by Users.updatedAt

    override val notNullId: Long = id.value
}
