package com.nitish.counter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.boot.Banner.Mode.OFF;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableWebMvc
@EnableConfigurationProperties
public class CounterApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication();
		springApplication.setBannerMode(OFF);
		springApplication.run(CounterApplication.class, args);
	}
}
