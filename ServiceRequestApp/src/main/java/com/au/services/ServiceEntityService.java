package com.au.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.au.repositories.ServiceRepository;

import com.au.models.ServiceEntity;

@Service
public class ServiceEntityService {
	@Autowired
	ServiceRepository serviceRepository;

	public List<ServiceEntity> getAllServices() {
		try {
			List<ServiceEntity> serviceList = serviceRepository.findAll();
			return serviceList;
		} catch (Exception e) {
			return null;
		}
	}

	public ServiceEntity addService(ServiceEntity serviceToAdd) {
		try {
			ServiceEntity addedService = serviceRepository.save(serviceToAdd);
			return addedService;
		} catch (Exception e) {
			System.out.println("------------------------------------------");
			e.printStackTrace();
			System.out.println("-------------------------------------------");
			return null;
		}
	}
}
