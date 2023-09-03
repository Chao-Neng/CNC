package art.relev.springboot3.cnc.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        License license = new License()
                .name("MIT");
        Info info = new Info()
                .title("CNC")
                .description("基于 Spring Boot 实现的 CNC")
                .version("v1.0.0")
                .license(license);
        ExternalDocumentation externalDocumentation = new ExternalDocumentation()
                .description("CNC 文档");
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .name("TOKEN")
                .in(SecurityScheme.In.HEADER);
        Components components = new Components()
                .addSecuritySchemes("token", securityScheme);
        OpenAPI openAPI = new OpenAPI()
                .info(info)
                .components(components)
                .externalDocs(externalDocumentation);
        return openAPI;
    }
}
