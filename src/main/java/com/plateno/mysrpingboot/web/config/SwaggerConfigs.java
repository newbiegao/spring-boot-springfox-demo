package com.plateno.mysrpingboot.web.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfigs {

  @Bean
  public Docket petApi() {
      return new Docket(DocumentationType.SWAGGER_2)
              .apiInfo(apiInfo())
              .select()
              .paths(petstorePaths())
              .build()
              .enableUrlTemplating(true) ;
  }

  @Bean
  public Docket categoryApi() {
      return new Docket(DocumentationType.SWAGGER_2)
              .groupName("user")
              .apiInfo(apiInfo())
              .select()
              .paths(userPaths())
              .build()
              .enableUrlTemplating(true);
  }


  private Predicate<String> userPaths() {
      return regex("/users.*");
  }


  private Predicate<String> allPaths() {

    return regex("/.*") ;
   
  }

  private Predicate<String> petstorePaths() {
    return or(
            regex("/srv.*"),
            regex("/srv1/.*"),
            regex("/srv1/.*")
    );

  } 

  private ApiInfo apiInfo() {
      return new ApiInfoBuilder()
              .title("Springfox petstore API")
              .description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum " +
                      "has been the industry's standard dummy text ever since the 1500s, when an unknown printer "
                      + "took a " +
                      "galley of type and scrambled it to make a type specimen book. It has survived not only five " +
                      "centuries, but also the leap into electronic typesetting, remaining essentially unchanged. " +
                      "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum " +
                      "passages, and more recently with desktop publishing software like Aldus PageMaker including " +
                      "versions of Lorem Ipsum.")
              .termsOfServiceUrl("http://springfox.io")
              .contact("springfox")
              .license("Apache License Version 2.0")
              .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
              .version("2.0")
              .build();
  }


}
