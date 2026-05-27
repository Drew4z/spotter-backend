package com.spotter_proyect.spotter.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title       = "Spotter API",
                version     = "1.0",
                description = "API REST del proyecto Spotter — plataforma de fitness y entrenadores personales",
                contact     = @Contact(name = "Spotter Team")
        ),
        servers = {
                @Server(url = "/api", description = "Servidor local")
        },
        security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
        name        = "bearerAuth",
        description = "Introduce el JWT obtenido en POST /api/auth/login. Formato: Bearer <token>",
        scheme      = "bearer",
        type        = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in          = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
    // La configuración se hace íntegramente vía anotaciones.
    // No se necesita ningún @Bean adicional con springdoc-openapi 2.x
}
