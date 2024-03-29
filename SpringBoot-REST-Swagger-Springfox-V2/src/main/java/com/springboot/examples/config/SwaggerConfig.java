package com.springboot.examples.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//@EnableSwagger2
//@EnableOpenApi
public class SwaggerConfig {

    @Bean
   public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.springboot.examples.controller"))
                .paths(PathSelectors.regex("/v1/employees/.*"))
                .build()
                .apiInfo(apiInfo());

   }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Employee API")
                .description("Employee API's")
                .version("1.0.0")
                .termsOfServiceUrl("http://employeeapi.com")
                .license("EmpOrg License")
                .licenseUrl("http://employeeapi.com")
                .build();
    }
}
