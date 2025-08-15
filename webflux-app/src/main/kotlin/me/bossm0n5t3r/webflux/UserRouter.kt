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
class UserRouter {
    @Bean
    fun userRoutes(
        userHandler: UserHandler,
        externalHandler: ExternalHandler,
    ): RouterFunction<ServerResponse> =
        router {
            "/webflux/users".nest {
                accept(MediaType.APPLICATION_JSON).nest {
                    GET("") { request -> mono { userHandler.getAllUsers(request) } }
                    GET("/search") { request -> mono { userHandler.searchUsersByName(request) } }
                    GET("/{id}") { request -> mono { userHandler.getUserById(request) } }
                    GET("/email/{email}") { request -> mono { userHandler.getUserByEmail(request) } }
                    POST("") { request -> mono { userHandler.createUser(request) } }
                    PUT("/{id}") { request -> mono { userHandler.updateUser(request) } }
                    DELETE("/{id}") { request -> mono { userHandler.deleteUser(request) } }
                    DELETE("") { request -> mono { userHandler.deleteAllUsers(request) } }
                }
            }

            "/webflux/external".nest {
                accept(MediaType.APPLICATION_JSON).nest {
                    // External API endpoints using coroutines
                    POST("/external/health") { request -> mono { externalHandler.callExternalHealthApi(request) } }
                    POST("/external/user/{userId}") { request -> mono { externalHandler.callExternalUserApi(request) } }
                    POST("/external/weather") { request -> mono { externalHandler.callExternalWeatherApi(request) } }
                    POST("/external/stock/{symbol}") { request -> mono { externalHandler.callExternalStockApi(request) } }
                    POST("/external/order/{orderId}") { request -> mono { externalHandler.callExternalOrderApi(request) } }
                    POST("/external/metrics") { request -> mono { externalHandler.callExternalMetricsApi(request) } }

                    // Get stored external API responses
                    GET("/external/responses") { request -> mono { externalHandler.getAllExternalApiResponses(request) } }
                    GET("/external/responses/endpoint") { request -> mono { externalHandler.getExternalApiResponsesByEndpoint(request) } }
                }
            }
        }
}
