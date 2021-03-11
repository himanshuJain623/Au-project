package com.au.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.au.models.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

	@Transactional
	@Modifying
	@Query(value = "update Customer_details c set c.customer_name = :customerName, c.password = :password, c.customer_phone = :customerPhone , c.customer_location = :customerLocation where c.customer_id = :customerId ", nativeQuery = true)
	void updateByCustomerId(Long customerId, String customerName, String password, String customerPhone,
			String customerLocation);

	CustomerEntity findByCustomerId(long id);

	@Query(value = "select exists(select * from customer_details c where c.customer_email= :userEmail)", nativeQuery = true)
	long findIfUser(String userEmail);

	@Query(value = "select c.customer_id from customer_details c where c.customer_email= :userEmail", nativeQuery = true)
	long findUserId(String userEmail);

	@Query(value = "select c.password from customer_details c where c.customer_email= :userEmail", nativeQuery = true)
	String getCustomerPassword(String userEmail);

}