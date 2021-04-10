package com.ex.clinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ImportResource
@SpringBootApplication
public class MyPetClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyPetClinicApplication.class, args);
	}

}
