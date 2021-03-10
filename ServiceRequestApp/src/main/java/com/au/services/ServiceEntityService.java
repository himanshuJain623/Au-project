package com.au.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.au.repositories.ServiceRepository;

import com.au.models.ServiceEntity;

@Service
public class ServiceEntityService {
	@Autowired
	ServiceRepository serviceRepository;

	private static final Logger logger = LoggerFactory.getLogger(ServiceEntityService.class);

	public List<ServiceEntity> getAllServices() {
		try {
			return serviceRepository.findAll();
		} catch (Exception e) {
			logger.debug("EXCEPTION IN GEETING SERVICES IN SERVICE_ENTITY_SERVICE", e);
			return null;

		}
	}

	public ServiceEntity addService(ServiceEntity serviceToAdd) {
		try {
			ServiceEntity sE = new ServiceEntity();
			sE.setServiceName(serviceToAdd.getServiceName());
			return serviceRepository.save(sE);
		} catch (Exception e) {
			logger.debug("EXCEPTION IN ADDING SERVICE IN SERVICE_ENTITY_SERVICE", e);
			return null;
		}
	}
}
