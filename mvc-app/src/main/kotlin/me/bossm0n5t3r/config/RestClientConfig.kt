package me.bossm0n5t3r.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfig {
    /**
     * Configure RestClient bean
     */
    @Bean
    fun restClient(): RestClient = RestClient.create()
}
