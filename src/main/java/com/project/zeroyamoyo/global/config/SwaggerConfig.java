package com.project.zeroyamoyo.global.config;

import com.project.zeroyamoyo.global.jwt.JwtFilter;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Generated;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Generated
@OpenAPIDefinition(
        servers = {
                @Server(url = "http://localhost:8080", description = "Local")
        },
        info = @Info(
                title = "야모여 Backend API",
                description = "야모여 프로젝트 백엔드 API 명세서",
                version = "0.0.0"
        )
)
@Configuration
public class SwaggerConfig {
    private static final String BEARER_TOKEN_PREFIX = "Bearer";

    @Bean
    public OpenAPI openAPI() {
        String jwtSchemeName = JwtFilter.AUTHORIZATION_HEADER;
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme(BEARER_TOKEN_PREFIX)
                        .bearerFormat("JWT"));

        return new OpenAPI()
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}
