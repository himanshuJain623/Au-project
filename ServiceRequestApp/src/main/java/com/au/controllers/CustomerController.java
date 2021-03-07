package com.au.controllers;

import com.au.models.CustomerBookingsEntity;
import com.au.models.CustomerEntity;
import com.au.models.RatingEntity;
import com.au.models.RatingEntityModel;
import com.au.models.ServiceEntity;
import com.au.services.CustomerEntityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerEntityService customerUser;

	@GetMapping("/hello")
	public String getHello() {
		return "hello";
	}

	@PostMapping("/signup")
	public void addUser(@RequestBody CustomerEntity user) {
		customerUser.saveCustomer(user);
	}

	@PostMapping("/update")
	public void updateCustomer(@RequestBody CustomerEntity user) {
		customerUser.updateCustomer(user);
	}

	@RequestMapping(value = "/getBookings/{customerId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<CustomerBookingsEntity>> getAllBookings(@PathVariable long customerId) {
		List<CustomerBookingsEntity> list = customerUser.getAllBookings(customerId);
		return new ResponseEntity<List<CustomerBookingsEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/rateService")
	public void rateService(@RequestBody RatingEntityModel ratingOfService) {
		customerUser.rateService(ratingOfService);
	}

}