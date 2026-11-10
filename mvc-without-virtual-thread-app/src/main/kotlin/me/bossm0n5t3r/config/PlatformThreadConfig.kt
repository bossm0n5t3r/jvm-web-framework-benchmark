package me.bossm0n5t3r.config

import java.util.concurrent.Executors
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.AsyncTaskExecutor
import org.springframework.core.task.support.TaskExecutorAdapter

/** Configuration for a fixed-size platform thread pool used for parallel external API calls */
@Configuration
class PlatformThreadConfig {
    companion object {
        private const val PLATFORM_THREAD_POOL_SIZE = 200
    }

    /** Configure fixed-size platform thread executor for async tasks */
    @Bean("platformThreadExecutor")
    fun asyncTaskExecutor(): AsyncTaskExecutor =
        TaskExecutorAdapter(Executors.newFixedThreadPool(PLATFORM_THREAD_POOL_SIZE))
}
