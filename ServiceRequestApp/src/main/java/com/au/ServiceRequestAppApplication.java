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
public class ServiceRequestAppApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ServiceRequestAppApplication.class, args);
	}
	
	@Autowired
	private ServiceRepository serviceRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		ServiceEntity service1 = new ServiceEntity(103l,"carpainter");
		this.serviceRepository.save(service1);
		
		ServiceEntity service2 = new ServiceEntity(103l,"carpainter");
		this.serviceRepository.save(service2);
	}
	
}
