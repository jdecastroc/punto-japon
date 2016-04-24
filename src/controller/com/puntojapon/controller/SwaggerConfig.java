package com.puntojapon.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger Configuration for the API Documentation
 * 
 * @author jdecastroc
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	/**
	 * Swagger configuration to document the API and make it accessible into the server
	 * 
	 * @return API documentation info
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	/**
	 * Api documentation main information
	 * @return API main information to be build in the API documentation service
	 */
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("PuntoJapon API",
				"La API de PuntoJapon permite la obtención de información referente a estudiar, trabajar y vivir en Japón en formato JSON mediante web crawling",
				" ",
				"La presenta API utilizar información proveniente de páginasa web de terceros relacionados con el impulso de la cultura en Japón por lo que no se hace responsable del contenido obtenido de manera automática.",
				"jdecastrocabello@gmail.com",
				"API Punto Japon by jdecastroc is licensed under a Creative Commons Reconocimiento-NoComercial 4.0 Internacional License. Creado a partir de la obra en https://bitbucket.org/jdecastroc/punto-japon.",
				"http://creativecommons.org/licenses/by-nc/4.0/");
		return apiInfo;
	}
}