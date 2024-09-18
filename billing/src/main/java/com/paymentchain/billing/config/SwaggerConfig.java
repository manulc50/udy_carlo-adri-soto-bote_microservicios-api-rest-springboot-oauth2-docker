package com.paymentchain.billing.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
    // Main Swagger config definition    
    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.groupName("digitalthinking-apis")
                .apiInfo(apiInfo())
                //set up JWT input
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.paymentchain"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag("Billing API", "All apis relating to billing service"),
                        new Tag("Invoice", "Make invoices"));
    }
    
    // Define all details for app info
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Digitalthinking Shool")
                .description("Digitalthinking Shool API reference for developers, teh error handler is implement using \n"
                        + " The IETF devised RFC 7807 effor, which creates a generalized error-handling schema.\n"
                        + "https://tools.ietf.org/html/rfc7807")
                .termsOfServiceUrl("http://www.linkedin.com/in/casotobotero")
                .contact(new Contact("Carlos Adrian Soto", "", "https://digitalthinking.biz/es/ada-enterprise-core#contactus"))
                .license("sotobotero License")
                .licenseUrl("https://digitalthinking.biz/es/ada-enterprise-core#contactus")
                .version("1.0")
                .build();
    } 

    // define API key to include the header   
    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }
    // configure JWT security with global Autorization Scope

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

}
