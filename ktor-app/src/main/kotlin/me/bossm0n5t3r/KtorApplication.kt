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
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import me.bossm0n5t3r.config.DatabaseFactory
import me.bossm0n5t3r.controller.externalApiRouting
import me.bossm0n5t3r.controller.userRouting
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

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.application.environment.log
                .error("Unhandled error", cause)
            call.respond(HttpStatusCode.InternalServerError)
        }
    }

    userRouting(userRepository)
    externalApiRouting(externalApiService)
}
