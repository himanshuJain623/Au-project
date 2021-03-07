package com.au.controllers;

import com.au.models.BookingEntity;
import com.au.models.BookingEntityModel;
import com.au.models.CustomerBookingsEntity;
import com.au.models.CustomerEntity;
import com.au.models.RatingEntity;
import com.au.models.RatingEntityModel;
import com.au.models.ServiceEntity;
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

	@Autowired
	BookingEntityService bookingService;

	@PostMapping("/signup")
	public ResponseEntity<CustomerEntity> addUser(@RequestBody CustomerEntity user) {
		CustomerEntity created_user = customerUser.saveCustomer(user);

		if (created_user == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return new ResponseEntity<>(created_user, HttpStatus.CREATED);
	}

	/*
	 * @PostMapping("/update") public void updateCustomer(@RequestBody
	 * CustomerEntity user) { customerUser.updateCustomer(user); }
	 */

	@RequestMapping(value = "/getBookings/{customerId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<CustomerBookingsEntity>> getAllBookings(@PathVariable long customerId) {
		List<CustomerBookingsEntity> list = customerUser.getAllBookings(customerId);

		if (list == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} else if (list.size() == 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return new ResponseEntity<List<CustomerBookingsEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/rateService")
	public ResponseEntity<RatingEntity> rateService(@RequestBody RatingEntityModel ratingOfService) {

		RatingEntity serviceRated = customerUser.rateService(ratingOfService);

		if (serviceRated == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return new ResponseEntity<>(serviceRated, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/serviceProviders/{serviceId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<ServiceProviderEntity>> serviceProviders(@PathVariable long serviceId) {
		List<ServiceProviderEntity> list = customerUser.serviceProviders(serviceId);
		if (list == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} else if (list.size() == 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return new ResponseEntity<List<ServiceProviderEntity>>(list, HttpStatus.OK);
	}

	@PostMapping("/bookService")
	public ResponseEntity<BookingEntity> bookService(@RequestBody BookingEntityModel book) {
		BookingEntity booked = bookingService.bookService(book);

		if (booked == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return new ResponseEntity<BookingEntity>(booked, HttpStatus.CREATED);

	}

}