package com.menighin.gatekeeper.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {

    private fun createAPIKeyScheme(): SecurityScheme {
        return SecurityScheme().type(SecurityScheme.Type.HTTP)
            .bearerFormat("JWT")
            .scheme("bearer")
    }

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI().addSecurityItem(
            SecurityRequirement().addList
                ("Bearer Authentication")
        )
        .components(Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
        .info(
            Info().title("My REST API")
                .description("Some custom description of API.")
                .version("1.0").contact(
                    Contact().name("Joao Menighin")
                        .email("joao.menighin@gmail.com")
                )
        )
    }

}