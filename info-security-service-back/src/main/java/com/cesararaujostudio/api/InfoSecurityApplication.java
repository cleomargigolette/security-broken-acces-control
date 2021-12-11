package com.cesararaujostudio.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InfoSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfoSecurityApplication.class, args);
	}

}
