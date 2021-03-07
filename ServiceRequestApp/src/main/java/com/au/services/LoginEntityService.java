package com.au.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.au.models.CustomerEntity;
import com.au.models.EmailPasswordEntity;
import com.au.models.ProviderEntity;
import com.au.models.ServiceEntity;
import com.au.models.UserTypeDetailEntity;
import com.au.repositories.CustomerRepository;
import com.au.repositories.ProviderRepository;

@Service
public class LoginEntityService {
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ProviderRepository providerRepository;

	public UserTypeDetailEntity getUserType(EmailPasswordEntity user) {

		UserTypeDetailEntity userType = new UserTypeDetailEntity();

		long checkUser = 0;
		long userId = -1;
		checkUser = customerRepository.findIfUser(user.getUserMail(), user.getUserPassword());

		if (checkUser == 1) {
			userType.setUserType("customer");
			userId = customerRepository.findUserId(user.getUserMail(), user.getUserPassword());
			CustomerEntity c = customerRepository.findByCustomerId(userId);

			userType.setUserEmail(c.getCustomerEmail());
			userType.setUserId(userId);
			userType.setUserLocation(c.getCustomerLocation());
			userType.setUserName(c.getCustomerName());
			userType.setUserPhone(c.getCustomerPhone());
		}

		if (checkUser == 0) {
			checkUser = providerRepository.findIfUser(user.getUserMail(), user.getUserPassword());

			if (checkUser == 1) {
				userType.setUserType("provider");
				userId = providerRepository.findUserId(user.getUserMail(), user.getUserPassword());
				ProviderEntity p = providerRepository.findByProviderId(userId);

				userType.setUserEmail(p.getProviderEmail());
				userType.setUserId(userId);
				userType.setUserLocation(p.getProviderLocation());
				userType.setUserName(p.getProviderName());
				userType.setUserPhone(p.getProviderPhone());
			} else {
				userType = null;
			}
		}

		return userType;
	}
}
