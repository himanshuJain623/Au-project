package com.au.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.au.models.ServiceEntity;
import com.au.models.ServiceProviderEntityModel;
import com.au.services.ServiceEntityService;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {

	@Autowired
	ServiceEntityService service;

	@GetMapping("/hello")
	public String getHello() {
		return "hello";
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<ServiceEntity>> getAllServices() {
		List<ServiceEntity> list = service.getAllServices();
		if(list==null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}else if(list.size()==0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return new ResponseEntity<List<ServiceEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/addService")
	public ResponseEntity addService(@RequestBody ServiceEntity serviceToAdd) {
		ServiceEntity addedService=service.addService(serviceToAdd);
		if(addedService==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
