package com.au.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.au.models.BookingEntity;
import com.au.models.CustomerBookingsEntity;
import com.au.models.CustomerEntity;
import com.au.models.RatingEntity;
import com.au.models.RatingEntityModel;
import com.au.models.ServiceEntity;
import com.au.models.ServiceProviderEntity;
import com.au.repositories.BookingRepository;
import com.au.repositories.CustomerRepository;
import com.au.repositories.ProviderRepository;
import com.au.repositories.RatingRepository;
import com.au.repositories.ServiceProviderRepository;
import com.au.repositories.ServiceRepository;

@Service
public class CustomerEntityService {

	@Autowired
	ServiceRepository serviceRepository;

	@Autowired
	ProviderRepository providerRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	ServiceProviderRepository serviceProviderRepository;

	@Autowired
	RatingRepository ratingRepository;

	public void saveCustomer(CustomerEntity user) {
		customerRepository.save(user);
	}

	public void updateCustomer(CustomerEntity user) {

		customerRepository.updateByCustomerId(user.getCustomerId(), user.getCustomerName(), user.getPassword(),
				user.getCustomerPhone(), user.getCustomerLocation());
	}

	public List<CustomerBookingsEntity> getAllBookings(long user) {

		List<BookingEntity> bookingData = bookingRepository.findByBookingId(user);

		List<CustomerBookingsEntity> bookingByCustomer = new ArrayList<CustomerBookingsEntity>();

		for (BookingEntity bE : bookingData) {
			CustomerBookingsEntity cBE = new CustomerBookingsEntity();

			/*
			 * System.out.println(bE.getProviderId().getProviderName());
			 * System.out.println(bE.getServiceId().getServiceName());
			 */
			
			// this needs to be added
			
//			cBE.setProviderName(bE.getProviderId().getProviderName());
//			cBE.setServiceName(bE.getServiceId().getServiceName());
			cBE.setBookingCost(bE.getBookingCost());
			cBE.setBookingDate(bE.getBookingDate());
			cBE.setBookingStatus(bE.getBookingStatus());

			bookingByCustomer.add(cBE);
		}

		return bookingByCustomer;
	}

	// When a customer books a service we create a copy of it in the rating table by
	// booking_id

	public void rateService(RatingEntityModel ratingOfService) {

		BookingEntity b = bookingRepository.findByBookingId(ratingOfService.getBookingId()).get(0);

		RatingEntity r = new RatingEntity();

		r.setBookingId(b);
		r.setRatingDescription(ratingOfService.getRatingDescription());
		r.setRatingPoints(ratingOfService.getRatingPoints());

		ratingRepository.save(r);
	}

}