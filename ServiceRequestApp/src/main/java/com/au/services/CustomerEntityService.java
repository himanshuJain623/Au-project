package com.au.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.au.models.CustomerEntity;
import com.au.repositories.CustomerRepository;

@Service
public class CustomerEntityService {

	@Autowired
	CustomerRepository customerRepository;
	
	public void saveCustomer(CustomerEntity user) {
		customerRepository.save(user);
	}
}
