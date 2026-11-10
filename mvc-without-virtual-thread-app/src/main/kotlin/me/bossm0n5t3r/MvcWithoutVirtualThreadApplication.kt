package me.bossm0n5t3r

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

/** Spring Boot Application for Spring MVC without Virtual Thread */
@SpringBootApplication @EnableAsync class MvcWithoutVirtualThreadApplication

fun main(args: Array<String>) {
    runApplication<MvcWithoutVirtualThreadApplication>(*args)
}
