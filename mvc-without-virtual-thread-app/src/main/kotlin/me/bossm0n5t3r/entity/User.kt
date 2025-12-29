package me.bossm0n5t3r.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import me.bossm0n5t3r.table.UserTable
import java.time.LocalDateTime

/**
 * JPA Entity for Spring MVC
 */
@Entity
@Table(name = "users")
open class User(
    @Column(nullable = false)
    override var name: String,
    @Column(nullable = false)
    override var email: String,
    @Column(name = "created_at")
    override var createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "updated_at")
    override var updatedAt: LocalDateTime = LocalDateTime.now(),
) : UserTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    override val notNullId: Long
        get() = requireNotNull(id) { "Entity must be persisted before accessing notNullId" }
}
