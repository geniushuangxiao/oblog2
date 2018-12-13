package com.summer.xblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 */
@Profile("dev")
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket testApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).useDefaultResponseMessages(false).select()
                .apis(RequestHandlerSelectors.basePackage("com.summer.xblog.controller")).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("xblog后台").contact(new Contact("", "", "")).build();
    }
}
