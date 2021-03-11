package com.au.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.au.models.ProviderEntity;

@Repository
public interface ProviderRepository extends JpaRepository<ProviderEntity, Long> {

	ProviderEntity findByProviderId(Long providerId);

	@Query(value = "select exists(select * from provider_details p where p.provider_email = :userEmail)", nativeQuery = true)
	long findIfUser(String userEmail);

	@Query(value = "select p.provider_id from provider_details p where p.provider_email= :userEmail", nativeQuery = true)
	long findUserId(String userEmail);

	@Query(value = "select p.password from provider_details p where p.provider_email= :userEmail", nativeQuery = true)
	String getProviderPassword(String userEmail);

}
