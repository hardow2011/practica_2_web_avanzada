package com.example.practica_2;

import com.example.practica_2.services.security.SecurityServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Practica2Application /*implements WebMvcConfigurer*/{

	public static void main(String[] args) {

		// SpringApplication.run(Practica2Application.class, args);

		ApplicationContext applicationContext = SpringApplication.run(Practica2Application.class, args);

		SecurityServices securityServices = (SecurityServices) applicationContext.getBean("securityServices");
		securityServices.createAdminUser();

	}
}
