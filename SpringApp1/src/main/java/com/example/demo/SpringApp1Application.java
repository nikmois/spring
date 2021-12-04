package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//MVC
//Model - data and working logic with them
//View - UI
//Controller - business logic of the app


@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class
})
public class SpringApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringApp1Application.class, args);
	}

}
