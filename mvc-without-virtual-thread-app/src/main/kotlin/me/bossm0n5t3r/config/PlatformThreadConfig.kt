package me.bossm0n5t3r.config

import java.util.concurrent.Executors
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.AsyncTaskExecutor
import org.springframework.core.task.support.TaskExecutorAdapter

/** Configuration for a fixed-size platform thread pool used for parallel external API calls */
@Configuration
class PlatformThreadConfig {
    /**
     * Configure fixed-size platform thread executor for async tasks.
     *
     * The pool size bounds throughput (about poolSize / 15 req/s), so it is configurable to compare
     * the default against a tuned value. See `benchmark.platform-thread-pool-size` in
     * `application.yaml`.
     */
    @Bean("platformThreadExecutor")
    fun asyncTaskExecutor(
        @Value($$"${benchmark.platform-thread-pool-size}") poolSize: Int,
    ): AsyncTaskExecutor = TaskExecutorAdapter(Executors.newFixedThreadPool(poolSize))
}
