package com.breno.transaction_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransactionApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(TransactionApiApplication.class, args);
		System.out.println("http://localhost:8080/transactions-web/form");
	}
}
