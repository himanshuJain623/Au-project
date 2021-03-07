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

	public List<BookingEntity> getProviderBookings(Long providerId) {
		String pId="_"+providerId;
		try {
			List<BookingEntity> providerBookings = bookingRepository.findByFspIdEndingWith(pId);
			return providerBookings;
		} catch (Exception e) {
			System.out.println(
					"------------------------------EXCEPTION IN GEETING ALL BOOKINGS BY PROVIDER---------------------------------");
			e.printStackTrace();
			System.out.println("-------------------------------------------------------------");
			return null;
		}
	}
}
