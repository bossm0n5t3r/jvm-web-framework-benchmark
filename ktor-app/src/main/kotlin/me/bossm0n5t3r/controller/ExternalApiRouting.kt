package me.bossm0n5t3r.controller

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import me.bossm0n5t3r.service.ExternalApiService

fun Application.externalApiRouting(externalApiService: ExternalApiService) {
    routing {
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
