package com.product.listtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableWebMvc
public class ProductListTracker {
	public static void main(String[] args) {
		SpringApplication.run(ProductListTracker.class, args);
	}
}
