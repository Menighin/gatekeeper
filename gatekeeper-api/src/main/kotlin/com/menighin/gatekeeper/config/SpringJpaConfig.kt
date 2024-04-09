package com.menighin.gatekeeper.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

@Configuration
class SpringJpaConfig {

    @Bean
    fun auditorAwareConfig() : AuditorAware<String> = AuditorAware<String> {
        val loggedInUser = SecurityContextHolder.getContext().authentication.name
        Optional.of(loggedInUser)
    }

}