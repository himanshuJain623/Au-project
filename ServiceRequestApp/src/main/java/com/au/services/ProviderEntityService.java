package com.au.services;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.au.models.BookingEntity;
import com.au.models.ProviderBookingsModel;
import com.au.models.ProviderDashboardModel;
import com.au.models.ProviderEntity;
import com.au.models.RatingEntity;
import com.au.models.ServiceEntity;
import com.au.models.ServiceProviderEntity;
import com.au.models.ServiceProviderEntityModel;
import com.au.models.ServiceVsBookingModel;
import com.au.models.ServiceVsCustomerModel;
import com.au.models.ServiceVsRevenueModel;
import com.au.repositories.BookingRepository;
import com.au.repositories.ProviderRepository;
import com.au.repositories.RatingRepository;
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

	@Autowired
	RatingRepository ratingRepository;

	private static final Logger logger = LoggerFactory.getLogger(ProviderEntityService.class);

	// for signing up provider
	public ProviderEntity saveProvider(ProviderEntity user) {
		try {
			ProviderEntity pE = new ProviderEntity();
			pE.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
			pE.setProviderEmail(user.getProviderEmail());
			pE.setProviderLocation(user.getProviderLocation());
			pE.setProviderName(user.getProviderName());
			pE.setProviderPhone(user.getProviderPhone());
			return providerRepository.save(pE);
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
			logger.debug("EXCEPTION IN ADDING SERVICE BY PROVIDER", e);
			return null;
		}
	}

	// for getting all services by provider
	public List<ServiceProviderEntity> getServiceByProvider(Long providerId) {

		try {
			ProviderEntity p = providerRepository.findByProviderId(providerId);
			return serviceProviderRespository.findByforeignProviderId(p);
		} catch (Exception e) {
			logger.debug("EXCEPTION IN GEETING ALL SERVICES BY PROVIDE", e);
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
			logger.debug("EXCEPTION IN GEETING SERVICES DETAILS PROVIDED BY PROVIDER", e);
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

					if (ratingRepository.findByBookingId(bE) != null) {
						RatingEntity re = ratingRepository.getRatingByBookingId(bE);
						booking.setRatingDescription(re.getRatingDescription());
						booking.setRatingPoints(re.getRatingPoints());
					}

					providerBookings.add(booking);

				}
			}
			return providerBookings;
		} catch (Exception e) {
			logger.debug("EXCEPTION IN GETTING BOOKING DETAILS PROVIDED BY PROVIDER IN PROVIDER_ENTITY_SERVICE", e);
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
			logger.debug("EXCEPTION IN UPDATING STATUS IN PROVIDER_ENTITY_SERVICE", e);
			return -1;
		}
	}

	public ProviderDashboardModel getDashboardDetails(Long providerId) {
		try {

			ProviderDashboardModel pdm = new ProviderDashboardModel();
			long ratingSum = 0;
			float averageRating;
			long numberOfRatings = 0;
			long totalRevenue = 0;
			long totalBookings = 0;
			List<ServiceVsBookingModel> sbList = new ArrayList<>();
			List<ServiceVsCustomerModel> scList = new ArrayList<>();
			List<ServiceVsRevenueModel> srList = new ArrayList<>();

			List<ServiceProviderEntity> speList = getServiceByProvider(providerId);

			for (ServiceProviderEntity spId : speList) {

				List<BookingEntity> providerBookingEntities = bookingEntityService.getServiceProviderBookings(spId);

				ServiceVsBookingModel sbm = new ServiceVsBookingModel();
				sbm.setServiceName(spId.getForeignServiceId().getServiceName());
				long serviceBookings = 0;

				ServiceVsRevenueModel srm = new ServiceVsRevenueModel();
				srm.setServiceName(spId.getForeignServiceId().getServiceName());
				long serviceRevenue = 0;

				ServiceVsCustomerModel scm = new ServiceVsCustomerModel();
				scm.setServiceName(spId.getForeignServiceId().getServiceName());
				scm.setNumberOfCustomer(bookingRepository.getProviderServiceCustomers(spId));

				for (BookingEntity bE : providerBookingEntities) {
					if (bE.getBookingStatus().equals("Completed")) {
						Long rs = ratingRepository.findByBookingId(bE);
						if (rs != null) {
							ratingSum += ratingRepository.getRatingPointsByBookingId(bE);
							numberOfRatings++;
						}
						totalRevenue += bE.getBookingCost();
						totalBookings++;
						serviceBookings++;
						serviceRevenue += bE.getBookingCost();
					}
				}
				sbm.setNumberOfBookings(serviceBookings);
				sbList.add(sbm);

				srm.setServiceRevenue(serviceRevenue);
				srList.add(srm);

				scList.add(scm);
			}

			if (numberOfRatings == 0) {
				averageRating = 0;
			} else {
				averageRating = (float) ((ratingSum * 1.0) / numberOfRatings);
			}

			pdm.setAverageRating(averageRating);
			pdm.setTotalBookings(totalBookings);
			pdm.setTotalRevenue(totalRevenue);
			pdm.setProviderServiceBookings(sbList);
			pdm.setProviderServiceCustomers(scList);
			pdm.setProviderServiceRevenue(srList);
			return pdm;
		} catch (Exception e) {
			logger.debug("EXCEPTION IN GETTING DASHBOARD DETAILS IN PROVIDER_ENTITY_SERVICE", e);
			return null;
		}
	}
}