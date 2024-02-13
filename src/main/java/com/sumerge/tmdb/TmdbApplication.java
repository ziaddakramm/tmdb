package com.sumerge.tmdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TmdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmdbApplication.class, args);
	}
}