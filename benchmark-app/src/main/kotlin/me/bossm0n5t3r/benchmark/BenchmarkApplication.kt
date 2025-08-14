package me.bossm0n5t3r.benchmark

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BenchmarkApplication

fun main(args: Array<String>) {
    SpringApplication.run(BenchmarkApplication::class.java, *args)
}
