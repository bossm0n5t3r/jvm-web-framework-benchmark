package me.bossm0n5t3r.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableJpaRepositories(basePackages = ["me.bossm0n5t3r.repository"])
@EntityScan(basePackages = ["me.bossm0n5t3r.entity"])
@EnableTransactionManagement
class JpaConfig
