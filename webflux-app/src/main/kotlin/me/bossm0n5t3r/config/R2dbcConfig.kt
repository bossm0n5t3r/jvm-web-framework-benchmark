package me.bossm0n5t3r.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@Configuration
@EnableR2dbcRepositories(basePackages = ["me.bossm0n5t3r.repository"])
class R2dbcConfig
