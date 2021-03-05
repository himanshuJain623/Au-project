package com.au.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.au.models.ProviderEntity;
import com.au.repositories.ProviderRepository;

@Service
public class ProviderEntityService {

	@Autowired
	ProviderRepository providerRepository;
	
	public void saveProvider(ProviderEntity user) {
		System.out.println(user);
		providerRepository.save(user);
	}
}
