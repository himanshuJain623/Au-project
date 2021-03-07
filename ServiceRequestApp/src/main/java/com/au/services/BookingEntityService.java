package com.au.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.au.models.BookingEntity;
import com.au.models.ProviderEntity;
import com.au.models.ServiceProviderEntity;
import com.au.repositories.BookingRepository;

@Service
public class BookingEntityService {

	@Autowired
	BookingRepository bookingRepository;

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
}
