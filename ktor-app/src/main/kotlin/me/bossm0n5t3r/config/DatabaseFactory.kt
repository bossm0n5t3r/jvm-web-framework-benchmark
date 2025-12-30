package me.bossm0n5t3r.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.ApplicationConfig
import me.bossm0n5t3r.model.ExternalApiResponses
import me.bossm0n5t3r.model.Users
import org.jetbrains.exposed.v1.core.StdOutSqlLogger
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

class DatabaseFactory(
    private val config: ApplicationConfig,
) {
    fun init(): HikariDataSource {
        val dbConfig = config.config("db")
        val hikariConfig =
            HikariConfig().apply {
                jdbcUrl = dbConfig.property("url").getString()
                username = dbConfig.property("username").getString()
                password = dbConfig.property("password").getString()
                driverClassName = dbConfig.property("driver").getString()
                maximumPoolSize =
                    dbConfig
                        .propertyOrNull("maxPoolSize")
                        ?.getString()
                        ?.toInt()
                        ?: 10
            }
        return HikariDataSource(hikariConfig)
    }

    val database: Database by lazy { Database.connect(init()) }

    fun createTables() =
        transaction(database) {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(Users, ExternalApiResponses)
        }

    fun dropTables() =
        transaction(database) {
            addLogger(StdOutSqlLogger)
            SchemaUtils.drop(Users, ExternalApiResponses)
        }
}
