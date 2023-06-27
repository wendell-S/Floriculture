package com.floriculture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.out;

@SpringBootApplication
public class FloricultureApplication {


	public static void main(String[] args) {
		SpringApplication.run(FloricultureApplication.class, args);
		out.println("oi");
	}
	
	


}
