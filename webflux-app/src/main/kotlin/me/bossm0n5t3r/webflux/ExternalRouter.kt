package me.bossm0n5t3r.webflux

import kotlinx.coroutines.reactor.mono
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

/**
 * Spring WebFlux Router Configuration
 */
@Configuration
class ExternalRouter {
    @Bean
    fun externalRoutes(externalHandler: ExternalHandler): RouterFunction<ServerResponse> =
        router {
            "/webflux/external".nest {
                accept(MediaType.APPLICATION_JSON).nest {
                    // External API endpoints using coroutines
                    GET("/health") { _ -> mono { externalHandler.callExternalHealthApi() } }
                    POST("") { _ -> mono { externalHandler.callExternalApi() } }
                    POST("/no-db") { _ -> mono { externalHandler.callExternalApiWithNoDatabase() } }
                }
            }
        }
}
