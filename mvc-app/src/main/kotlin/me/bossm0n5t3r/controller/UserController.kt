package me.bossm0n5t3r.controller

import me.bossm0n5t3r.dto.UserRequest
import me.bossm0n5t3r.entity.User
import me.bossm0n5t3r.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

/**
 * Spring MVC Controller for traditional blocking operations
 */
@RestController
@RequestMapping("/mvc/users")
class UserController(
    private val userRepository: UserRepository,
) {
    @GetMapping
    fun getAllUsers(): List<User> = userRepository.findAllOrderByCreatedAtDesc()

    @GetMapping("/{id}")
    fun getUserById(
        @PathVariable id: Long,
    ): ResponseEntity<User> {
        val user = userRepository.findById(id)
        return if (user.isPresent) {
            ResponseEntity.ok(user.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/search")
    fun searchUsersByName(
        @RequestParam name: String,
    ): List<User> = userRepository.findByNameContaining(name)

    @GetMapping("/email/{email}")
    fun getUserByEmail(
        @PathVariable email: String,
    ): ResponseEntity<User> {
        val user = userRepository.findByEmail(email)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createUser(
        @RequestBody userRequest: UserRequest,
    ): ResponseEntity<User> {
        val user =
            User(
                name = userRequest.name,
                email = userRequest.email,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
            )
        val savedUser = userRepository.save(user)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser)
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody userRequest: UserRequest,
    ): ResponseEntity<User> {
        val existingUser = userRepository.findById(id)
        return if (existingUser.isPresent) {
            val updatedUser =
                existingUser.get().apply {
                    name = userRequest.name
                    email = userRequest.email
                    updatedAt = LocalDateTime.now()
                }
            val savedUser = userRepository.save(updatedUser)
            ResponseEntity.ok(savedUser)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUser(
        @PathVariable id: Long,
    ): ResponseEntity<Void> =
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }

    @DeleteMapping
    fun deleteAllUsers(): ResponseEntity<Void> {
        userRepository.deleteAll()
        return ResponseEntity.noContent().build()
    }
}
