package com.example.modu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)//Security 비활성화
public class ModuApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModuApplication.class, args);
	}

}
