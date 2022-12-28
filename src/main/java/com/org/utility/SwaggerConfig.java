package com.org.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//@EnableSwagger2
public class SwaggerConfig
{
	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2).groupName("product-catalog-billing").apiInfo(apiInfo()).select()
				.paths(PathSelectors.ant("/*/**")).apis(RequestHandlerSelectors.basePackage("com.org.utility")).build();
	}

	private ApiInfo apiInfo()
	{
		return new ApiInfoBuilder().title("Product catalog and billing API")
				.description("App deals with catalog and billing").licenseUrl("taurus.vamsi@gmail.com").version("1.0")
				.build();
	}
}
