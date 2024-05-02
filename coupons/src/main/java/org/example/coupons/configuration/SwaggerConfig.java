package org.example.coupons.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API", version = "v1", description = "Documentation APIs v1"))
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-api")
                .packagesToScan("org.example.coupons")
                .addOpenApiCustomizer(openApi -> {
                    openApi.info(new io.swagger.v3.oas.models.info.Info()
                            .title("API Documentation")
                            .version("v1")
                            .description("API documentation for the backend"));
                })
                .build();
    }
}