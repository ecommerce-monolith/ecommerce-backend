package com.huyhn.ecommerce_backend.config;

import java.util.List;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import com.huyhn.ecommerce_backend.exception.ErrorResponse;

import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    private static final String ERROR_RESPONSE = "ErrorResponse";

    @Bean
    public OpenAPI customOpenAPI() {
        Components components = new Components()
                .addSecuritySchemes("bearerAuth",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                                .bearerFormat("JWT"))
                .addSecuritySchemes("apiKey",
                        new SecurityScheme().type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER)
                                .name("X-API-Key"));

        ModelConverters.getInstance().read(ErrorResponse.class)
                .forEach(components::addSchemas);

        return new OpenAPI()
                .info(new Info()
                        .title("Ecommerce System API")
                        .description("Ecommerce System API")
                        .version("1.0.0"))
                .servers(List.of(new Server().url("http://localhost:8888")))
                .components(components)
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }

    @Bean
    public OperationCustomizer globalErrorResponsesCustomizer() {
        Content errorContent = new Content().addMediaType(
                MediaType.APPLICATION_JSON_VALUE,
                new io.swagger.v3.oas.models.media.MediaType()
                        .schema(new Schema<>().$ref("#/components/schemas/" + ERROR_RESPONSE)));

        return (operation, handlerMethod) -> {
            operation.getResponses().addApiResponse("400", errorResponse("Bad Request", errorContent));
            operation.getResponses().addApiResponse("403", errorResponse("Forbidden", errorContent));
            operation.getResponses().addApiResponse("500", errorResponse("Internal Server Error", errorContent));
            return operation;
        };
    }

    private static ApiResponse errorResponse(String description, Content content) {
        return new ApiResponse().description(description).content(content);
    }
}
