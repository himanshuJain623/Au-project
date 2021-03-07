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

	public CustomerEntity saveCustomer(CustomerEntity user) {

		try {
			CustomerEntity created_user = customerRepository.save(user);
			System.out.println(created_user.getCustomerLocation());
			return created_user;
		} catch (Exception e) {
			System.out.println(
					"------------------------------EXCEPTION IN SAVE CUSTOMER IN CUSTOMER_ENTITY_SERVICE---------------------------------");
			e.printStackTrace();
			System.out.println("-------------------------------------------------------------");
			return null;
		}
	}

	public void updateCustomer(CustomerEntity user) {

		customerRepository.updateByCustomerId(user.getCustomerId(), user.getCustomerName(), user.getPassword(),
				user.getCustomerPhone(), user.getCustomerLocation());
	}

	public List<CustomerBookingsEntity> getAllBookings(long user) {

		try {

			CustomerEntity c = customerRepository.findByCustomerId(user);

			List<BookingEntity> bookingData = bookingRepository.findByCustomerId(c);

			List<CustomerBookingsEntity> bookingByCustomer = new ArrayList<CustomerBookingsEntity>();

			for (BookingEntity bE : bookingData) {
				CustomerBookingsEntity cBE = new CustomerBookingsEntity();

				cBE.setProviderName(bE.getSpId().getForeignProviderId().getProviderName());
				cBE.setServiceName(bE.getSpId().getForeignServiceId().getServiceName());
				cBE.setBookingCost(bE.getBookingCost());
				cBE.setBookingDate(bE.getBookingDate());
				cBE.setBookingStatus(bE.getBookingStatus());

				bookingByCustomer.add(cBE);
			}

			return bookingByCustomer;

		} catch (Exception e) {
			System.out.println(
					"------------------------------EXCEPTION IN GEETING ALL SERVICES BY PROVIDER---------------------------------");
			e.printStackTrace();
			System.out.println("-------------------------------------------------------------");
			return null;
		}

	}

	// When a customer books a service we create a copy of it in the rating table by
	// booking_id

	public RatingEntity rateService(RatingEntityModel ratingOfService) {

		try {

			BookingEntity b = bookingRepository.findByBookingId(ratingOfService.getBookingId());
			if (b.getBookingStatus().equals("Completed")) {
				System.out.println("inside here");
				System.out.println(b.getBookingStatus());
				RatingEntity r = new RatingEntity();

				r.setBookingId(b);
				r.setRatingDescription(ratingOfService.getRatingDescription());
				r.setRatingPoints(ratingOfService.getRatingPoints());

				r = ratingRepository.save(r);
				return r;
			}else {
				return null;
			}
		} catch (Exception e) {
			System.out.println(
					"------------------------------EXCEPTION IN RATING SERVICE IN CUSTOMER_ENTITY_SERVICE---------------------------------");
			e.printStackTrace();
			System.out.println("-------------------------------------------------------------");
			return null;
		}

	}

	public List<ServiceProviderEntity> serviceProviders(long serviceId) {
		try {
			ServiceEntity s = serviceRepository.findByServiceId(serviceId);

			List<ServiceProviderEntity> providersOfService = serviceProviderRepository.findByforeignServiceId(s);

			return providersOfService;
		} catch (Exception e) {
			System.out.println(
					"------------------------------EXCEPTION IN GETIING SERVICE PROVIDERS IN CUSTOMER_ENTITY_SERVICE---------------------------------");
			e.printStackTrace();
			System.out.println("-------------------------------------------------------------");
			return null;
		}
	}
}