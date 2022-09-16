package com.tamkiraly.linkedinlight;

import com.tamkiraly.linkedinlight.controllers.ClientController;
import com.tamkiraly.linkedinlight.controllers.PositionController;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan(basePackageClasses = {
    ClientController.class, PositionController.class})
public class SwaggerConfig {

  @Bean
  public Docket configureSwagger() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.tamkiraly"))
        .build()
        .apiInfo(createAPIInfo());
  }

  private ApiInfo createAPIInfo() {
    return new ApiInfo(
        "LinkedIn Light",
        "Job searching application for Bredex GMBH.",
        "v1.0.1",
        "https://en.wikipedia.org/wiki/Terms_of_service",
        new Contact("Tamás Király", "https://github.com/tamkiraly", "tamaskiraly90@gmail.com"),
        "License",
        "https://www.grammarly.com/blog/licence-license/",
        Collections.emptyList());
  }

  @Bean
  public InternalResourceViewResolver defaultViewResolver() {
    return new InternalResourceViewResolver();
  }
}
