package com.au.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.au.models.BookingEntity;
import com.au.models.BookingEntityModel;
import com.au.models.ServiceProviderEntity;
import com.au.repositories.BookingRepository;
import com.au.repositories.CustomerRepository;
import com.au.repositories.ServiceProviderRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BookingEntityService {

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	ServiceProviderRepository serviceProviderRepository;

	@Autowired
	CustomerRepository customerRepository;

	private static final Logger logger = LoggerFactory.getLogger(BookingEntityService.class);

	public List<BookingEntity> getServiceProviderBookings(ServiceProviderEntity spId) {
		try {
			return bookingRepository.findBySpId(spId);
		} catch (Exception e) {
			logger.debug("EXCEPTION IN GETTING ALL BOOKINGS BY PROVIDER IN BOOKING_ENTITY_SERVICE",e);
			return null;
		}
	}

	public BookingEntity bookService(BookingEntityModel book) {

		try {

			BookingEntity b = new BookingEntity();

			ServiceProviderEntity sPE = serviceProviderRepository.findBySpId(book.getSpId());

			b.setBookingCost(sPE.getPrice());
			b.setBookingDate();
			b.setBookingStatus("Processing");
			b.setCustomerId(customerRepository.findByCustomerId(book.getCustomerId()));
			b.setSpId(sPE);
			return bookingRepository.save(b);
		} catch (Exception e) {
			logger.debug("EXCEPTION IN BOOKING SERVICE IN BOOKING_ENTITY_SERVICE",e);
			return null;
		}
	}
}
