package com.au.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.au.models.BookingEntity;
import com.au.models.CustomerEntity;
import com.au.models.ServiceProviderEntity;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

	BookingEntity findByBookingId(Long bookingId);

	List<BookingEntity> findByCustomerId(CustomerEntity customer);

	List<BookingEntity> findBySpId(ServiceProviderEntity spId);

	// for updating booking status
	@Transactional
	@Modifying
	@Query("update BookingEntity bE set bE.bookingStatus = ?2 where bE.bookingId = ?1")
	void updateBookingStatus(long bookingId, String bookingStatus);

	@Query(value = "select count(distinct b.customer_Id) from bookings_details b where b.fsp_Id = :fspId", nativeQuery = true)
	long getProviderServiceCustomers(ServiceProviderEntity fspId);

}