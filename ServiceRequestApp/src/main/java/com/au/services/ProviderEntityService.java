package com.au.services;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.au.models.ProviderEntity;
import com.au.models.ServiceEntity;
import com.au.models.ServiceProviderEntity;
import com.au.models.ServiceProviderEntityModel;
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

	// for signing up provider
	public ProviderEntity saveProvider(ProviderEntity user) {
		try {
			ProviderEntity created_user = providerRepository.save(user);
			return created_user;
		} catch (Exception e) {
			return null;
		}
	}

	// for adding service by provider
	public ServiceProviderEntity addServiceByProvider(ServiceProviderEntityModel serviceToAdd) {

		ServiceProviderEntity serviceToBeAdded;

		ServiceEntity s = serviceRepository.findByServiceId(serviceToAdd.getServiceId());
		ProviderEntity p = providerRepository.findByProviderId(serviceToAdd.getProviderId());

		serviceToBeAdded = new ServiceProviderEntity(s, p, serviceToAdd.getServiceDescription(),
				serviceToAdd.getDiscount(), serviceToAdd.getPrice());

		try {
			serviceProviderRespository.save(serviceToBeAdded);
			return serviceToBeAdded;
		} catch (Exception e) {
			System.out.println(
					"------------------------------EXCEPTION IN GEETING ALL SERVICES BY PROVIDER---------------------------------");
			e.printStackTrace();
			System.out.println("-------------------------------------------------------------");
			return null;
		}
	}

	// for getting all services by provider
	public List<ServiceProviderEntity> getServiceByProvider(Long providerId) {

		try {
			ProviderEntity p = providerRepository.findByProviderId(providerId);
			List<ServiceProviderEntity> servicesByProvider = serviceProviderRespository.findByforeignProviderId(p);
			return servicesByProvider;
		} catch (Exception e) {
			System.out.println(
					"------------------------------EXCEPTION IN GEETING ALL SERVICES BY PROVIDER---------------------------------");
			e.printStackTrace();
			System.out.println("-------------------------------------------------------------");
			return null;
		}
	}

	// for getting service details provided by provider
	public ServiceProviderEntity getServiceDetails(Long providerId, Long serviceId) {

		try {
			ProviderEntity p = providerRepository.findByProviderId(providerId);
			ServiceEntity s = serviceRepository.findByServiceId(serviceId);
			ServiceProviderEntity serviceDetails = serviceProviderRespository
					.findByforeignProviderIdAndForeignServiceId(p, s);
			return serviceDetails;
		} catch (Exception e) {
			System.out.println(
					"------------------------------EXCEPTION IN GEETING SERVICES DETAILS PROVIDED BY PROVIDER---------------------------------");
			e.printStackTrace();
			System.out.println("-------------------------------------------------------------");
			return null;
		}
	}

}