package me.bossm0n5t3r.repository

import me.bossm0n5t3r.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

/**
 * JPA Repository for Spring MVC
 */
@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?

    fun findByNameContaining(name: String): List<User>

    @Query("SELECT u FROM User u ORDER BY u.createdAt DESC")
    fun findAllOrderByCreatedAtDesc(): List<User>
}
