package com.au.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.au.models.ServiceEntity;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
	ServiceEntity findByServiceName(String serviceName);

//	@Query(value = "SELECT * FROM SERVICE_DETAILS s where s.serviceId = :id", nativeQuery = true)
//	List<ServiceEntity> findServiceById(@Param("id") Long id);

	ServiceEntity findByServiceId(Long serviceId);
}
