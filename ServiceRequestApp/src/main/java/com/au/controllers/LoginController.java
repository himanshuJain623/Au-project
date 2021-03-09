package com.au.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.au.models.EmailPasswordEntity;
import com.au.models.UserTypeDetailEntity;
import com.au.services.LoginEntityService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	LoginEntityService login;

	@PostMapping("/customer")
	public ResponseEntity<UserTypeDetailEntity> getCustomer(@RequestBody EmailPasswordEntity user) {

		UserTypeDetailEntity details = login.getCustomer(user);
		if (details == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return new ResponseEntity<>(details, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/provider")
	public ResponseEntity<UserTypeDetailEntity> getProvider(@RequestBody EmailPasswordEntity user) {

		UserTypeDetailEntity details = login.getProvider(user);
		if (details == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return new ResponseEntity<>(details, new HttpHeaders(), HttpStatus.OK);
	}

}