package com.chatroom.dummywhatsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@EnableSwagger2
public class DummyWhatsappApplication {

	public static void main(String[] args) {
		SpringApplication.run(DummyWhatsappApplication.class, args);
	}

}
