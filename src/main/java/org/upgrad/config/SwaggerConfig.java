package org.upgrad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.upgrad.controllers.*;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@ComponentScan(basePackageClasses = {UserController.class,AdminController.class,AnswerController.class, CommentController.class,
               CommonController.class,LikeFollowController.class,QuestionController.class})
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket productsApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.upgrad.controllers"))
                .paths(PathSelectors.regex("/api.*"))
                .build();
    }
}
