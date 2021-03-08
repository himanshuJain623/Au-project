package com.au.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.au.models.BookingEntity;
import com.au.models.ProviderBookingsModel;
import com.au.models.ProviderEntity;
import com.au.models.ServiceEntity;
import com.au.models.ServiceProviderEntity;
import com.au.models.ServiceProviderEntityModel;
import com.au.repositories.BookingRepository;
import com.au.repositories.ProviderRepository;
import com.au.repositories.ServiceProviderRepository;
import com.au.repositories.ServiceRepository;

@Service
public class ProviderEntityService {

	@Autowired
	ProviderRepository providerRepository;

	@Autowired
	ServiceProviderRepository serviceProviderRespository;

	@Autowired
	ServiceRepository serviceRepository;

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	BookingEntityService bookingEntityService;

	private static final Logger logger = LoggerFactory.getLogger(ProviderEntityService.class);

	// for signing up provider
	public ProviderEntity saveProvider(ProviderEntity user) {
		try {
			return providerRepository.save(user);
		} catch (Exception e) {
			return null;
		}
	}

	// for adding service by provider
	public ServiceProviderEntity addServiceByProvider(ServiceProviderEntityModel serviceToAdd) {

		try {
			ServiceProviderEntity serviceToBeAdded;

			ServiceEntity s = serviceRepository.findByServiceId(serviceToAdd.getServiceId());
			ProviderEntity p = providerRepository.findByProviderId(serviceToAdd.getProviderId());

			serviceToBeAdded = new ServiceProviderEntity(s, p, serviceToAdd.getServiceDescription(),
					serviceToAdd.getDiscount(), serviceToAdd.getPrice());
			return serviceProviderRespository.save(serviceToBeAdded);
		} catch (Exception e) {
			logger.debug(
					"------------------------------EXCEPTION IN ADDING SERVICE BY PROVIDER---------------------------------");
			e.printStackTrace();
			logger.debug("-------------------------------------------------------------");
			return null;
		}
	}

	// for getting all services by provider
	public List<ServiceProviderEntity> getServiceByProvider(Long providerId) {

		try {
			ProviderEntity p = providerRepository.findByProviderId(providerId);
			return serviceProviderRespository.findByforeignProviderId(p);
		} catch (Exception e) {
			logger.debug(
					"------------------------------EXCEPTION IN GEETING ALL SERVICES BY PROVIDER---------------------------------");
			e.printStackTrace();
			logger.debug("-----------------------------END--------------------------------");
			return null;
		}
	}

	// for getting single service details provided by provider
	public ServiceProviderEntity getServiceDetails(Long providerId, Long serviceId) {

		try {
			ProviderEntity p = providerRepository.findByProviderId(providerId);
			ServiceEntity s = serviceRepository.findByServiceId(serviceId);
			return serviceProviderRespository.findByforeignProviderIdAndForeignServiceId(p, s);

		} catch (Exception e) {
			logger.debug(
					"------------------------------EXCEPTION IN GEETING SERVICES DETAILS PROVIDED BY PROVIDER---------------------------------");
			e.printStackTrace();
			logger.debug("-----------------------------END--------------------------------");
			return null;
		}
	}

	// for getting all bookings of a provider
	public List<ProviderBookingsModel> getproviderBookings(Long providerId) {
		try {
			List<ServiceProviderEntity> speList = getServiceByProvider(providerId);
			List<ProviderBookingsModel> providerBookings = new ArrayList<>();
			for (ServiceProviderEntity spId : speList) {

				List<BookingEntity> providerBookingEntities = bookingEntityService.getServiceProviderBookings(spId);

				for (BookingEntity bE : providerBookingEntities) {
					ProviderBookingsModel booking = new ProviderBookingsModel();

					booking.setBookingId(bE.getBookingId());
					booking.setBookingStatus(bE.getBookingStatus());
					booking.setBookingDate(bE.getBookingDate());
					booking.setBookingCost(bE.getBookingCost());
					booking.setCustomerName(bE.getCustomerId().getCustomerName());
					booking.setServiceName(bE.getSpId().getForeignServiceId().getServiceName());
					booking.setProviderName(bE.getSpId().getForeignProviderId().getProviderName());

					providerBookings.add(booking);

				}
			}
			return providerBookings;
		} catch (Exception e) {
			logger.debug(
					"------------------------------EXCEPTION IN GETTING BOOKING DETAILS PROVIDED BY PROVIDER IN PROVIDER_ENTITY_SERVICE---------------------------------");
			e.printStackTrace();
			logger.debug("----------------------------END---------------------------------");
			return null;
		}
	}

//	to update booking status by provider
	public int updateBookingSatus(Long bookingId, String status) {
		try {
			BookingEntity b = bookingRepository.findByBookingId(bookingId);
			if (b.getBookingStatus().equals("Completed")) {
				return 0;
			}
			bookingRepository.updateBookingStatus(bookingId, status);
			return 1;
		} catch (Exception e) {
			logger.debug(
					"------------------------------EXCEPTION IN UPDATING STATUS IN PROVIDER_ENTITY_SERVICE---------------------------------");
			e.printStackTrace();
			logger.debug("----------------------------END---------------------------------");
			return -1;
		}
	}
}