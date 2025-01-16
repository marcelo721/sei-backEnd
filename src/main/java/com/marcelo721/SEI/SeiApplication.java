package com.marcelo721.SEI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SeiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeiApplication.class, args);
	}

}
