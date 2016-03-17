package com.nitish.counter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String TITLE = "Seach Text Api";
    public static final String DESCRIPTION = "Used for getting frequency of text present on server";
    public static final String VERSION = "1.0.0";

    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo(TITLE, DESCRIPTION,VERSION, "", new Contact("","",""), "", ""))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/counter-api/search"))
                .paths(PathSelectors.regex("/counter-api/top/*"))
                .build();
    }
}
