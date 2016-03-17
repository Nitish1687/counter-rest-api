package com.nitish.counter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

import static org.springframework.boot.Banner.Mode.OFF;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CounterApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication();
		springApplication.setBannerMode(OFF);
		springApplication.run(CounterApplication.class, args);
	}
}
