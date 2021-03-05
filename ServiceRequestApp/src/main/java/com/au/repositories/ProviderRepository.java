package com.au.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.au.models.ProviderEntity;

@Repository
public interface ProviderRepository extends JpaRepository<ProviderEntity, Long> {

}
