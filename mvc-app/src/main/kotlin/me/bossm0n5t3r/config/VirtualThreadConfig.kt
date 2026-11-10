package me.bossm0n5t3r.config

import java.util.concurrent.Executors
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.AsyncTaskExecutor
import org.springframework.core.task.support.TaskExecutorAdapter

/** Configuration for JVM Virtual Threads and REST client */
@Configuration
class VirtualThreadConfig {
    /** Configure Virtual Thread executor for async tasks */
    @Bean("virtualThreadExecutor")
    fun asyncTaskExecutor(): AsyncTaskExecutor =
        TaskExecutorAdapter(Executors.newVirtualThreadPerTaskExecutor())
}
