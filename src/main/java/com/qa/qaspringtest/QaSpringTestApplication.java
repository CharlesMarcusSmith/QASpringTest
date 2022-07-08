package com.qa.qaspringtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc																//http://localhost:8080/swagger-ui/index.html FOR API ENDPOINT DIAGRAM
@SpringBootApplication
public class QaSpringTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(QaSpringTestApplication.class, args);
	}

}
