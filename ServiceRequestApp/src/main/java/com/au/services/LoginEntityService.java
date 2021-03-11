package com.au.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.au.models.CustomerEntity;
import com.au.models.EmailPasswordEntity;
import com.au.models.ProviderEntity;
import com.au.models.UserTypeDetailEntity;
import com.au.repositories.CustomerRepository;
import com.au.repositories.ProviderRepository;

@Service
public class LoginEntityService {
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ProviderRepository providerRepository;

	private boolean checkPass(String plainPassword, String hashedPassword) {
		if (BCrypt.checkpw(plainPassword, hashedPassword)) {
			System.out.println("The password matches.");
			return true;
		} else {
			System.out.println("The password does not match.");
			return false;
		}
	}

	public UserTypeDetailEntity getCustomer(EmailPasswordEntity user) {

		UserTypeDetailEntity userType = new UserTypeDetailEntity();

		long checkUser = 0;
		long userId = -1;
		checkUser = customerRepository.findIfUser(user.getUserMail());
		if (checkUser == 1) {
			if (checkPass(user.getUserPassword(), customerRepository.getCustomerPassword(user.getUserMail()))) {
				userType.setUserType("customer");
				userId = customerRepository.findUserId(user.getUserMail());
				CustomerEntity c = customerRepository.findByCustomerId(userId);

				userType.setUserEmail(c.getCustomerEmail());
				userType.setUserId(userId);
				userType.setUserLocation(c.getCustomerLocation());
				userType.setUserName(c.getCustomerName());
				userType.setUserPhone(c.getCustomerPhone());

			} else {
				userType = null;
			}
		} else {
			userType = null;
		}
		return userType;

	}

	public UserTypeDetailEntity getProvider(EmailPasswordEntity user) {

		UserTypeDetailEntity userType = new UserTypeDetailEntity();

		long checkUser = 0;
		long userId = -1;
		checkUser = providerRepository.findIfUser(user.getUserMail());

		if (checkUser == 1) {
			if (checkPass(user.getUserPassword(), providerRepository.getProviderPassword(user.getUserMail()))) {
				userType.setUserType("provider");
				userId = providerRepository.findUserId(user.getUserMail());
				ProviderEntity p = providerRepository.findByProviderId(userId);

				userType.setUserEmail(p.getProviderEmail());
				userType.setUserId(userId);
				userType.setUserLocation(p.getProviderLocation());
				userType.setUserName(p.getProviderName());
				userType.setUserPhone(p.getProviderPhone());
			} else {
				userType = null;
			}
		} else {
			userType = null;
		}

		return userType;
	}

}