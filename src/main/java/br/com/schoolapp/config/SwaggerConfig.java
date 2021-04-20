package br.com.schoolapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("br.com.schoolapp.controller"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(metaInfo())
//                .useDefaultResponseMessages(true);

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.schoolapp.controller"))
                .paths(PathSelectors.any())
                .build();
    }

//    private ApiInfo metaInfo() {
//        ApiInfo apiInfo = new ApiInfo(
//                "School App",
//                "API REST de gerenciamento escolar.",
//                "1.0",
//                "Terms of Service",
//                new Contact("Marcelo Duarte", "",
//                        "marcelohaduarte@gmail.com"),
//                "Apache License Version 2.0",
//                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
//        );
//
//        return apiInfo;
//    }

}
