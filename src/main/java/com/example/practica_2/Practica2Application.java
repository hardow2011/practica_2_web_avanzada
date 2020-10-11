package com.example.practica_2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Practica2Application {

	public static void main(String[] args) {
		SpringApplication.run(Practica2Application.class, args);

		try {
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String todayString = simpleDateFormat.format(new Date());
			Date today = simpleDateFormat.parse(todayString);
			Date parsedReturnDate = simpleDateFormat.parse("2020-11-27");
			long diff = TimeUnit.MILLISECONDS.toDays(parsedReturnDate.getTime() - today.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
