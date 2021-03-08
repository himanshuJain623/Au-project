package com.au.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.au.models.ServiceEntity;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
	ServiceEntity findByServiceName(String serviceName);

	ServiceEntity findByServiceId(Long serviceId);
}
