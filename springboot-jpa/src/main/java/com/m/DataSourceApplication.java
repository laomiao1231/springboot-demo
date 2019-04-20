package com.m;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DataSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataSourceApplication.class, args);
	}

}
