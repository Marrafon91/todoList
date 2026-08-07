package io.github.marrafon91.todoList.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TodoList API")
                        .version("1.0")
                        .description("API REST do projeto TodoList")
                        .contact(new Contact()
                                .name("Guilherme Marrafon")
                                .url("https://github.com/Marrafon91")));
    }
}
