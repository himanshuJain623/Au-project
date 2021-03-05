package com.au.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.au.repositories.ServiceRepository;

public class UniqueServiceNameValidator implements ConstraintValidator<UniqueServiceName, String> {
	@Autowired
	private ServiceRepository serviceRepository;

	@Override
	public void initialize(UniqueServiceName constraintAnnotation) {
	}

	@Override
	public boolean isValid(String serviceName, ConstraintValidatorContext context) {
		if (serviceRepository == null) {
			return true;
		}
		return serviceRepository.findByServiceName(serviceName) == null;
	}
}
