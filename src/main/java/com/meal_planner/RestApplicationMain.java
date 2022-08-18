package com.meal_planner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class RestApplicationMain {

	private static final Logger logger = LoggerFactory.getLogger(RestApplicationMain.class);

	public static void main(String[] args) {
		SpringApplication springApplication =
				new SpringApplication(RestApplicationMain.class);
		final var applicationContext = springApplication.run(args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/meals").allowedOrigins("http://localhost:3000");
			}
		};
	}
}
