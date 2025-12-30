package me.bossm0n5t3r

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.jackson.jackson
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationStarted
import io.ktor.server.application.ApplicationStopped
import io.ktor.server.application.install
import io.ktor.server.netty.EngineMain
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import me.bossm0n5t3r.config.DatabaseFactory
import me.bossm0n5t3r.dto.UserRequest
import me.bossm0n5t3r.repository.ExternalApiResponseRepository
import me.bossm0n5t3r.repository.UserRepository
import me.bossm0n5t3r.service.ExternalApiService

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    val databaseFactory = DatabaseFactory(environment.config)
    monitor.subscribe(ApplicationStarted) { application ->
        application.environment.log.info("Server is started")
        databaseFactory.createTables()
    }
    monitor.subscribe(ApplicationStopped) { application ->
        application.environment.log.info("Server is stopped")
        databaseFactory.dropTables()
    }
    val database = databaseFactory.database
    val userRepository = UserRepository(database)
    val externalRepository = ExternalApiResponseRepository(database)
    val externalApiService = ExternalApiService(externalRepository)

    install(ContentNegotiation) {
        jackson {
            registerModule(JavaTimeModule())
        }
    }

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

        route("/ktor/external") {
            get("/health") {
                externalApiService.callHealthApi()
                call.respond(HttpStatusCode.OK)
            }
            post {
                call.respond(externalApiService.callExternalApiWithDatabase())
            }
            get("/no-db") {
                call.respond(externalApiService.callExternalApiWithNoDatabase())
            }
        }
    }
}
