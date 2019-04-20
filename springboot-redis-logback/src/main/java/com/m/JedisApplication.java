package com.m;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(JedisApplication.class, args);
	}

}

