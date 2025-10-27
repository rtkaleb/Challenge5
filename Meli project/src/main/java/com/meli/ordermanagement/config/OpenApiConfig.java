package com.meli.ordermanagement.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Swagger (OpenAPI) documentation.
 * Provides metadata for the generated API documentation.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MELI - Order Management System API")
                        .version("1.0.0")
                        .description("This is a RESTful API for managing orders, developed for the Digital NAO Challenge. " +
                                "It provides a complete set of endpoints for CRUD operations on orders.")
                        .contact(new Contact()
                                .name("Arturo Bandini")
                                .email("arturo.bandini.dev@example.com")
                                .url("https://github.com/Juan-Lucio/challeng-5"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}