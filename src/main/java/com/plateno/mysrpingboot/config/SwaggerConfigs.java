package com.plateno.mysrpingboot.config;

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
              .title(" Spring boot 集成 Springfox 测试项目")
              .description(" spring boot 通过 springfox 展示API文档并且可以在线测试 ")
              .termsOfServiceUrl("http://springfox.io")
              .license("Apache License Version 2.0")
              .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
              .version("2.0")
              .build();
  }


}
