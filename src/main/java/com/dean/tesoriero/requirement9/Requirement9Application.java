package com.dean.tesoriero.requirement9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:secret.properties")
public class Requirement9Application {

	public static void main(String[] args) {
		SpringApplication.run(Requirement9Application.class, args);
	}
}
