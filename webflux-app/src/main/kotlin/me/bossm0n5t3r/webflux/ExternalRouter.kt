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
                    POST("/health") { request -> mono { externalHandler.callExternalHealthApi(request) } }
                    POST("/user/{userId}") { request -> mono { externalHandler.callExternalUserApi(request) } }
                    POST("/weather") { request -> mono { externalHandler.callExternalWeatherApi(request) } }
                    POST("/stock/{symbol}") { request -> mono { externalHandler.callExternalStockApi(request) } }
                    POST("/order/{orderId}") { request -> mono { externalHandler.callExternalOrderApi(request) } }
                    POST("/metrics") { request -> mono { externalHandler.callExternalMetricsApi(request) } }

                    // Get stored external API responses
                    GET("/responses") { request -> mono { externalHandler.getAllExternalApiResponses(request) } }
                    GET("/responses/endpoint") { request -> mono { externalHandler.getExternalApiResponsesByEndpoint(request) } }
                }
            }
        }
}
