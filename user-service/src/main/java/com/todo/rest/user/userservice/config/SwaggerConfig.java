package com.todo.rest.user.userservice.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.todo.rest.user.userservice.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "User Services", 
	      "Use the following:<br>"
	      + "<li> GET: /category/{id} - to get specefic todo item </li>"
	      + "<li> POST: /category - with todo Object will create/update the existing todo item </li>"
	      + "<li> GET: /category/list - will get all the incomplete todos list </li>"
	      + "<li> DELETE: /category/{id} - will delete the specific todo item", 
	      "API TOS", 
	      "Terms of service", 
	      new Contact("John Doe", "www.example.com", "myeaddress@company.com"), 
	      "License of API", "API license URL", Collections.emptyList());
	}
}