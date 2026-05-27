package com.spotter_proyect.spotter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
import org.springframework.boot.security.autoconfigure.UserDetailsServiceAutoConfiguration;

@SpringBootApplication
public class SpotterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpotterApplication.class, args);
	}

}
