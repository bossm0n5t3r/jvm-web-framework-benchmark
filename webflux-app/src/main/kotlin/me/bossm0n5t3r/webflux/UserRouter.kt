package me.bossm0n5t3r.webflux

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
open class UserRouter {
    @Bean
    open fun userRoutes(userHandler: UserHandler): RouterFunction<ServerResponse> =
        router {
            "/webflux/users".nest {
                accept(MediaType.APPLICATION_JSON).nest {
                    GET("", userHandler::getAllUsers)
                    GET("/{id}", userHandler::getUserById)
                    GET("/search", userHandler::searchUsersByName)
                    GET("/email/{email}", userHandler::getUserByEmail)
                    POST("", userHandler::createUser)
                    PUT("/{id}", userHandler::updateUser)
                    DELETE("/{id}", userHandler::deleteUser)
                }
            }
        }
}
