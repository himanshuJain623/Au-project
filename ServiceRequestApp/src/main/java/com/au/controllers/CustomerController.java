package com.au.controllers;

import com.au.models.ServiceEntity;
import com.au.services.ServiceEntityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	ServiceEntityService service;

	@GetMapping("/hello")
	public String getHello() {
		return "hello";
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<ServiceEntity>> getAllServices() {
		List<ServiceEntity> list = service.getAllServices();
		return new ResponseEntity<List<ServiceEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

}