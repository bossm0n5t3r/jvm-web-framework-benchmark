package me.bossm0n5t3r.controller

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import me.bossm0n5t3r.dto.UserRequest
import me.bossm0n5t3r.repository.UserRepository
import kotlin.text.isNullOrBlank
import kotlin.text.toLongOrNull

fun Application.userRouting(userRepository: UserRepository) {
    routing {
        route("/ktor/users") {
            get {
                call.respond(userRepository.findAllOrderByCreatedAtDesc())
            }
            get("/search") {
                val name = call.request.queryParameters["name"]
                if (name.isNullOrBlank()) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
                call.respond(userRepository.findByNameContaining(name))
            }
            get("/{id}") {
                val id = call.parameters["id"]?.toLongOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
                val user = userRepository.findById(id)
                if (user == null) {
                    call.respond(HttpStatusCode.NotFound)
                } else {
                    call.respond(user)
                }
            }
            get("/email/{email}") {
                val email = call.parameters["email"]
                if (email.isNullOrBlank()) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
                val user = userRepository.findByEmail(email)
                if (user == null) {
                    call.respond(HttpStatusCode.NotFound)
                } else {
                    call.respond(user)
                }
            }
            post {
                val request = call.receive<UserRequest>()
                val created = userRepository.create(request)
                call.respond(HttpStatusCode.Created, created)
            }
            put("/{id}") {
                val id = call.parameters["id"]?.toLongOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@put
                }
                val request = call.receive<UserRequest>()
                val updated = userRepository.update(id, request)
                if (updated == null) {
                    call.respond(HttpStatusCode.NotFound)
                } else {
                    call.respond(updated)
                }
            }
            delete("/{id}") {
                val id = call.parameters["id"]?.toLongOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@delete
                }
                val deleted = userRepository.deleteById(id)
                if (deleted) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
            delete {
                userRepository.deleteAll()
                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}
