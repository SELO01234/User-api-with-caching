package com.example.SpringPostgresRedis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringPostgresRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPostgresRedisApplication.class, args);
	}

}
