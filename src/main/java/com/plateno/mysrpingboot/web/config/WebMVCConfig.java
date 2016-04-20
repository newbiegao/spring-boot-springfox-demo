package com.plateno.mysrpingboot.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.plateno.mysrpingboot.web.Interceptor.MyInterceptor;

@Configuration
public class WebMVCConfig {

  @Autowired
  private MyInterceptor myInterceptor ;
  
  @Bean
  public WebMvcConfigurer mvcConfig() {

    return new WebMvcConfigurerAdapter() {
      
      @Override
      public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/index/**") ;
      }
    };

  }

}
