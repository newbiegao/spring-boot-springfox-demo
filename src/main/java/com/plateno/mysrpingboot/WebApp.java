package com.plateno.mysrpingboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * WEB APP 应用
 * @author gaolk
 *
 */
@SpringBootApplication
@ServletComponentScan
@EnableSwagger2
public class WebApp {

	public static void main(String[] args) {
		 SpringApplication.run(WebApp.class, args);
	}
	
}
