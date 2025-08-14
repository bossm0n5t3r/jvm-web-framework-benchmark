package me.bossm0n5t3r.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.AsyncTaskExecutor
import org.springframework.core.task.support.TaskExecutorAdapter
import org.springframework.web.client.RestClient
import java.util.concurrent.Executors

/**
 * Configuration for JVM Virtual Threads and REST client
 */
@Configuration
class VirtualThreadConfig {
    /**
     * Configure Virtual Thread executor for async tasks
     */
    @Bean("virtualThreadExecutor")
    fun asyncTaskExecutor(): AsyncTaskExecutor = TaskExecutorAdapter(Executors.newVirtualThreadPerTaskExecutor())

    /**
     * Configure RestClient bean
     */
    @Bean
    fun restClient(): RestClient = RestClient.create()
}
