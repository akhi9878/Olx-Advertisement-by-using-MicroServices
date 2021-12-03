package com.olx;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@EnableEurekaClient
public class OlxLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlxLoginApplication.class, args);
	}
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	@Bean
	public Docket getCustomizedDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.olx"))
		//		.paths(PathSelectors.ant("/olx"))    the urls with only /olx path
				.paths(PathSelectors.any())
				.build();
	} 
	
	public ApiInfo getApiInfo() {
		return new ApiInfo("Olx - Login API Documentation",
				"API Documentation Released by Zensar Technologies",
				"1.0",
				"http://zensar.com",
				new Contact("Akhilesh","http://akhilesh.com","akhilesh.dodla@zensar.com"),
				"GPL",
				"http://gpl.com",
				new ArrayList<VendorExtension>());
	}
}
