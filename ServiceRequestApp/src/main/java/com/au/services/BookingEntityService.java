package com.au.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.au.models.BookingEntity;
import com.au.models.BookingEntityModel;
import com.au.models.ProviderEntity;
import com.au.models.ServiceProviderEntity;
import com.au.repositories.BookingRepository;
import com.au.repositories.CustomerRepository;
import com.au.repositories.ServiceProviderRepository;

@Service
public class BookingEntityService {

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	ServiceProviderRepository serviceProviderRepository;

	@Autowired
	CustomerRepository customerRepository;

	public List<BookingEntity> getServiceProviderBookings(ServiceProviderEntity spId) {
		try {
			List<BookingEntity> providerBookings = bookingRepository.findBySpId(spId);
			return providerBookings;
		} catch (Exception e) {
			System.out.println(
					"------------------------------EXCEPTION IN GETTING ALL BOOKINGS BY PROVIDER IN BOOKING_ENTITY_SERVICE---------------------------------");
			e.printStackTrace();
			System.out.println("-------------------------------------------------------------");
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
			System.out.println(
					"------------------------------EXCEPTION IN BOOKING SERVICE IN BOOKING_ENTITY_SERVICE---------------------------------");
			e.printStackTrace();
			System.out.println("-------------------------------------------------------------");
			return null;
		}
	}
}
