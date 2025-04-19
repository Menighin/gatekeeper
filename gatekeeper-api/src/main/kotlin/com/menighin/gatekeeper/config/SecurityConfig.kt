package com.menighin.gatekeeper.config

import com.menighin.gatekeeper.filter.JwtAuthenticationFilter
import com.menighin.gatekeeper.service.JwtService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.core.GrantedAuthorityDefaults
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
class SecurityConfig(
    private val jwtService: JwtService,
    private val authenticationProvider: AuthenticationProvider
) {

    companion object {
        val OPEN_ENDPOINTS = listOf(
            "/sanity-check",
//        "/roles/**",
            "/users/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/"
        )
    }


    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        val jwtAuthenticationFilter = JwtAuthenticationFilter(jwtService)

        return http
            .authorizeHttpRequests { authz ->
                OPEN_ENDPOINTS.forEach {
                    authz.requestMatchers(it).permitAll()
                }
                authz.anyRequest().authenticated()
            }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .csrf { csrf ->
                csrf.disable()
            }
            .build()
    }

    @Bean
    fun grantedAuthorityDefaults(): GrantedAuthorityDefaults? {
        return GrantedAuthorityDefaults("") // Remove the ROLE_ prefix
    }

}