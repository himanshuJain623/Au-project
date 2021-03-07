package com.au.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.au.models.ProviderEntity;
import com.au.models.ServiceEntity;
import com.au.models.ServiceProviderEntity;

@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProviderEntity, Long> {

	List<ServiceProviderEntity> findByforeignProviderId(ProviderEntity providerId);

	List<ServiceProviderEntity> findByforeignServiceId(ServiceEntity serviceId);

	ServiceProviderEntity findBySpId(String spId);

	ServiceProviderEntity findByforeignProviderIdAndForeignServiceId(ProviderEntity providerId,
			ServiceEntity serviceId);
}