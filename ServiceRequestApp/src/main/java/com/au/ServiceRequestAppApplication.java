package com.au;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import com.au.models.ServiceEntity;
import com.au.repositories.ServiceRepository;

@SpringBootApplication
public class ServiceRequestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRequestAppApplication.class, args);
	}

}
