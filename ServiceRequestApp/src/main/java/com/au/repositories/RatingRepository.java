package com.au.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.au.models.BookingEntity;
import com.au.models.RatingEntity;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, Long> {

	@Transactional
	@Modifying
	@Query(value = "insert into ratings_details r values r.description = :description, r.rating = :ratingPoints , r.booking_id = :bookingId ", nativeQuery = true)
	void saveRatingByOrderId(Long bookingId, String description, Long ratingPoints);

	@Query(value = "select r.rating from ratings_details r where r.foreign_booking_Id = :bookingId", nativeQuery = true)
	long getRatingPointsByBookingId(BookingEntity bookingId);

	@Query(value = "select (distinct r.rating_id) from ratings_details r where r.foreign_booking_Id = :bookingId", nativeQuery = true)
	RatingEntity findByBookingId(BookingEntity bE);
}