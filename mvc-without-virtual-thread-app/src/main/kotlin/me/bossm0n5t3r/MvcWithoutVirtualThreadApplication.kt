package me.bossm0n5t3r

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Spring Boot Application for Spring MVC without Virtual Thread
 */
@SpringBootApplication
class MvcWithoutVirtualThreadApplication

fun main(args: Array<String>) {
    runApplication<MvcWithoutVirtualThreadApplication>(*args)
}
