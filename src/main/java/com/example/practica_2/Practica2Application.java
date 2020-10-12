package com.example.practica_2;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@SpringBootApplication
public class Practica2Application /*implements WebMvcConfigurer*/{

	public static void main(String[] args) {
		SpringApplication.run(Practica2Application.class, args);

		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		// messageSource.setBasenames("lang/res");

		System.out.println("\n\n\n");
		// This is necesary since we chenaged the default path of the messages_XX.properties
		messageSource.setBasenames("lang/messages");
		// System.out.println(messageSource.getMessage("equipmentsList", null, new Locale("es")));
		// System.out.println(messageSource.getMessage("equipmentsList", null, Locale.ENGLISH));

	}

	// To store the locales in cookies
	// @Bean // <--- 1
	// public LocaleResolver localeResolver() {
	// 	CookieLocaleResolver localeResolver = new CookieLocaleResolver(); // <--- 2
	// 	localeResolver.setDefaultLocale(Locale.ENGLISH); // <--- 3
	// 	return localeResolver;
	// }

	// // To know which vale to look for in HTML to determine the language
	// @Bean
	// public LocaleChangeInterceptor localeChangeInterceptor() {
	// 	LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	// 	localeChangeInterceptor.setParamName("lang");
	// 	return localeChangeInterceptor;
	// }

	// @Override
	// public void addInterceptors(InterceptorRegistry interceptorRegistry) {
	// 	interceptorRegistry.addInterceptor(localeChangeInterceptor());
	// }

}
