package com.au.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.au.models.ServiceProviderEntity;

@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProviderEntity, Long> {

}