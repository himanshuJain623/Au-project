package com.au.controllers;

import com.au.models.BookingEntity;
import com.au.models.BookingEntityModel;
import com.au.models.CustomerBookingsEntity;
import com.au.models.CustomerEntity;
import com.au.models.RatingEntity;
import com.au.models.RatingEntityModel;
import com.au.models.ServiceProviderEntity;
import com.au.services.BookingEntityService;
import com.au.services.CustomerEntityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerEntityService customerUser;

	@Autowired
	BookingEntityService bookingService;

	@PostMapping("/signup")
	public ResponseEntity<CustomerEntity> addUser(@RequestBody CustomerEntity user) {
		CustomerEntity createdUser = customerUser.saveCustomer(user);

		if (createdUser == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@GetMapping("/getBookings/{customerId}")
	public ResponseEntity<List<CustomerBookingsEntity>> getAllBookings(@PathVariable long customerId) {
		List<CustomerBookingsEntity> list = customerUser.getAllBookings(customerId);

		if (list == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} else if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/rateService")
	public ResponseEntity<HttpStatus> rateService(@RequestBody RatingEntityModel ratingOfService) {

		RatingEntity serviceRated = customerUser.rateService(ratingOfService);

		if (serviceRated == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@GetMapping("/serviceProviders/{serviceId}")
	public ResponseEntity<List<ServiceProviderEntity>> serviceProviders(@PathVariable long serviceId) {
		List<ServiceProviderEntity> list = customerUser.serviceProviders(serviceId);
		if (list == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} else if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping("/bookService")
	public ResponseEntity<BookingEntity> bookService(@RequestBody BookingEntityModel book) {
		BookingEntity booked = bookingService.bookService(book);

		if (booked == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return new ResponseEntity<>(booked, HttpStatus.CREATED);

	}

}