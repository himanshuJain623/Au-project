package com.au.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.au.models.ProviderEntity;

@Repository
public interface ProviderRepository extends JpaRepository<ProviderEntity, Long> {

	@Query(value = "SELECT * FROM PROVIDER_DETAILS p where p.providerId = :id", nativeQuery = true)
	List<ProviderEntity> findProviderById(@Param("id") Long id);

//	ProviderEntity findByProviderId(long id);

	ProviderEntity findByProviderId(Long providerId);
}
