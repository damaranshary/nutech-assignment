package com.nutech.nutechassignment;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Nutech Integrasi Assignment Project",
                description = "<h3>Take Home Test API Programmer (Java Programmer - Springboot REST API)</h3>"
        )
)
@SecurityScheme(name = "Token Bearer", scheme = "bearer", bearerFormat = "JWT", type = SecuritySchemeType.HTTP)
@SpringBootApplication
public class NutechAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(NutechAssignmentApplication.class, args);
    }

}
