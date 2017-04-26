package com.amazon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {
		"com.amazon.controller"
})
public class ReviewApplication {
	public static void main(String[] args) {
		SpringApplication.run(ReviewApplication.class, args);
	}
}
