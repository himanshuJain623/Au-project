package com.au.services;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

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

	public void saveProvider(ProviderEntity user) {
		System.out.println(user);
		providerRepository.save(user);
	}

	public void getServicesByProvider(ServiceProviderEntityModel serviceToAdd) {

		ServiceProviderEntity serviceToBeAdded = new ServiceProviderEntity();

		serviceToBeAdded.setDiscount(serviceToAdd.getDiscount());
		serviceToBeAdded.setPrice(serviceToAdd.getPrice());
		serviceToBeAdded.setServiceDescription(serviceToAdd.getServiceDescription());

		ServiceEntity s = serviceRepository.findByServiceId(serviceToAdd.getServiceId());
		ProviderEntity p = providerRepository.findByProviderId(serviceToAdd.getProviderId());

		serviceToBeAdded.setServiceId(s);
		serviceToBeAdded.setProviderId(p);
		
		serviceProviderRespository.save(serviceToBeAdded);
	}
}