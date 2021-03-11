package com.au.services;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	private static final Logger logger = LoggerFactory.getLogger(CustomerEntityService.class);

	public CustomerEntity saveCustomer(CustomerEntity user) {

		try {
			CustomerEntity cE = new CustomerEntity();
			cE.setCustomerName(user.getCustomerName());
			cE.setCustomerEmail(user.getCustomerEmail());
			cE.setCustomerLocation(user.getCustomerLocation());
			cE.setCustomerPhone(user.getCustomerPhone());
			cE.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
			return customerRepository.save(cE);

		} catch (Exception e) {
			logger.debug("EXCEPTION IN SAVE CUSTOMER IN CUSTOMER_ENTITY_SERVICE", e);
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

			List<CustomerBookingsEntity> bookingByCustomer = new ArrayList<>();

			for (BookingEntity bE : bookingData) {
				CustomerBookingsEntity cBE = new CustomerBookingsEntity();

				cBE.setProviderName(bE.getSpId().getForeignProviderId().getProviderName());
				cBE.setServiceName(bE.getSpId().getForeignServiceId().getServiceName());
				cBE.setBookingCost(bE.getBookingCost());
				cBE.setBookingDate(bE.getBookingDate());
				cBE.setBookingStatus(bE.getBookingStatus());
				cBE.setBookingId(bE.getBookingId());
				bookingByCustomer.add(cBE);
			}

			return bookingByCustomer;

		} catch (Exception e) {
			logger.debug("EXCEPTION IN GEETING ALL SERVICES BY PROVIDER", e);
			return null;
		}

	}

	// When a customer books a service we create a copy of it in the rating table by
	// booking_id

	public RatingEntity rateService(RatingEntityModel ratingOfService) {

		try {

			BookingEntity b = bookingRepository.findByBookingId(ratingOfService.getBookingId());
			if (b.getBookingStatus().equals("Completed")) {

				RatingEntity r;
				if (ratingRepository.findByBookingId(b) == null) {
					r = new RatingEntity();
					r.setBookingId(b);
				} else {
					r = ratingRepository.getRatingByBookingId(b);
				}
				r.setRatingDescription(ratingOfService.getRatingDescription());
				r.setRatingPoints(ratingOfService.getRatingPoints());
				r = ratingRepository.save(r);
				return r;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.debug("EXCEPTION IN RATING SERVICE IN CUSTOMER_ENTITY_SERVICE", e);
			return null;
		}

	}

	public List<ServiceProviderEntity> serviceProviders(long serviceId) {
		try {
			ServiceEntity s = serviceRepository.findByServiceId(serviceId);

			return serviceProviderRepository.findByforeignServiceId(s);

		} catch (Exception e) {
			logger.debug("EXCEPTION IN GETIING SERVICE PROVIDERS IN CUSTOMER_ENTITY_SERVICE", e);
			return null;
		}
	}
}