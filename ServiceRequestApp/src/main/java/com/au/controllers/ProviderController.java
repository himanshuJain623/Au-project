package com.au.controllers;

import com.au.models.BookingEntity;
import com.au.models.ProviderBookingsModel;
import com.au.models.ProviderEntity;
import com.au.models.ServiceProviderEntity;
import com.au.models.ServiceProviderEntityModel;
import com.au.services.ProviderEntityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
public class ProviderController {
	@Autowired
	ProviderEntityService provider;

	@GetMapping("/hello")
	public String getHello() {
		return "hello";
	}

	@PostMapping("/signup")
	public ResponseEntity<ProviderEntity> addUser(@RequestBody ProviderEntity user) {
		ProviderEntity created_user = provider.saveProvider(user);
		if (created_user == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return new ResponseEntity<>(created_user, HttpStatus.CREATED);
	}

	@PostMapping("/service/addService")
	public ResponseEntity<ServiceProviderEntity> addService(@RequestBody ServiceProviderEntityModel serviceToAdd) {
		ServiceProviderEntity service_added = provider.addServiceByProvider(serviceToAdd);
		if (service_added == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return new ResponseEntity<>(service_added, HttpStatus.CREATED);
	}

	@GetMapping("/service/all")
	public ResponseEntity<List<ServiceProviderEntity>> getProviderServices(@RequestParam Long providerId) {
		List<ServiceProviderEntity> servicesByProvider = provider.getServiceByProvider(providerId);
		if (servicesByProvider == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} else if (servicesByProvider.size() == 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return new ResponseEntity<List<ServiceProviderEntity>>(servicesByProvider, HttpStatus.OK);
	}

	@GetMapping("/service/single")
	public ResponseEntity<ServiceProviderEntity> getServiceDetailsProvider(@RequestParam Long providerId,
			@RequestParam Long serviceId) {
		ServiceProviderEntity service = provider.getServiceDetails(providerId, serviceId);
		if (service == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return new ResponseEntity<ServiceProviderEntity>(service, HttpStatus.OK);
	}

	@GetMapping("/bookings/all")
	public ResponseEntity<List<ProviderBookingsModel>> getProviderBookings(@RequestParam Long providerId) {
		List<ProviderBookingsModel> providerBookings = provider.getproviderBookings(providerId);
		if (providerBookings == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} else if (providerBookings.size() == 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return new ResponseEntity<List<ProviderBookingsModel>>(providerBookings, HttpStatus.OK);
	}

	@PutMapping("/booking/updateStatus")
	public ResponseEntity updateBookingSatus(@RequestParam Long bookingId, @RequestParam String status) {
		int updatedStatus = provider.updateBookingSatus(bookingId, status);
		if (updatedStatus == -1) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} else if (updatedStatus == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return new ResponseEntity<>(updatedStatus, HttpStatus.CREATED);
	}
}