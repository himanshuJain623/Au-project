package com.au.repositories;

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

	@Query("select r.ratingPoints from RatingEntity r where r.bookingId = ?1")
	long getRatingPointsByBookingId(BookingEntity bookingId);

	@Query("select r from RatingEntity r where r.bookingId = ?1")
	RatingEntity getRatingByBookingId(BookingEntity bookingId);

	@Query("select r.ratingId from RatingEntity r where r.bookingId = ?1")
	Long findByBookingId(BookingEntity bookingId);

}