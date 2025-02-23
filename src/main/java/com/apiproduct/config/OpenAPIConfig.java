package com.apiproduct.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .title("API PRODUCT")
                .description("API PRODUCT ON CLOUD")
                .version("1.0.0");

        return new OpenAPI().info(info);
    }
}
