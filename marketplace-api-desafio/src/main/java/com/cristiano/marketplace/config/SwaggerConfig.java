package com.cristiano.marketplace.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

	@Bean
	public Docket api() {

		// Incluindo na chamada do swagger, a chave Autorization no header
		final String swaggerToken = "";    
		List<Parameter> params = new ArrayList<Parameter>();
		params.add(new ParameterBuilder()
				.name("Authorization")
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.required(true)
				.hidden(true)
				.defaultValue("Bearer " + swaggerToken)
				.build()
				);

		return new Docket(DocumentationType.SWAGGER_2).globalOperationParameters(params)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.cristiano.marketplace"))
				.build()
				.apiInfo(metaData());

	}



	private ApiInfo metaData() {
		return new ApiInfoBuilder()
				.title("Spring Boot REST API")
				.description("\"Spring Boot REST - API Produtos\"")
				.version("1.0.0")
				.license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
				.build();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
		.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}