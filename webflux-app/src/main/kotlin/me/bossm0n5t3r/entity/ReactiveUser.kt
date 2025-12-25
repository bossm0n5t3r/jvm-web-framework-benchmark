package me.bossm0n5t3r.entity

import me.bossm0n5t3r.table.UserTable
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

/**
 * R2DBC Entity for Spring WebFlux
 */
@Table("users")
data class ReactiveUser(
    @Id
    val id: Long = 0,
    @Column("name")
    override var name: String,
    @Column("email")
    override var email: String,
    @Column("created_at")
    override var createdAt: LocalDateTime = LocalDateTime.now(),
    @Column("updated_at")
    override var updatedAt: LocalDateTime = LocalDateTime.now(),
) : UserTable {
    override val notNullId: Long = id
}
