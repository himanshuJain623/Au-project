package com.au.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.au.models.EmailPasswordEntity;
import com.au.models.ServiceEntity;
import com.au.models.UserTypeDetailEntity;
import com.au.services.LoginEntityService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	LoginEntityService login;

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<UserTypeDetailEntity> getUserType(@RequestBody EmailPasswordEntity user) {

		UserTypeDetailEntity details = login.getUserType(user);
		if (details == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return new ResponseEntity<UserTypeDetailEntity>(details, new HttpHeaders(), HttpStatus.OK);
	}

}
