package lv.nixx.poc.security.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI openAPI = new OpenAPI();
        return openAPI.info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("PoC project for OpenAPI")
                .description("Simple API to show how OpenAPI works")
                .version("1.0.0");
    }

}