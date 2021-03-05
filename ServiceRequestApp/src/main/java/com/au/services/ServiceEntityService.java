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
		List<ServiceEntity> serviceList = serviceRepository.findAll();
		if (serviceList.size() > 0) {
			return serviceList;
		} else {
			return new ArrayList<ServiceEntity>();
		}
	}
}
