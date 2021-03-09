package com.au.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "RATINGS_DETAILS")
public class RatingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ratingId;

	@NotBlank
	@Column(name = "description")
	private String ratingDescription;

	@NotNull
	@NumberFormat
	@Range(min = 0, max = 10)
	@Column(name = "Rating")
	private long ratingPoints;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "foreignBookingId", referencedColumnName = "bookingId", unique = true)
	private BookingEntity bookingId;

	public RatingEntity() {
		super();
	}

	public RatingEntity(long ratingId, BookingEntity bookingId, @NotBlank String ratingDescription,
			@NotNull @Range(min = 0, max = 10) long ratingPoints) {
		super();
		this.ratingId = ratingId;
		this.bookingId = bookingId;
		this.ratingDescription = ratingDescription;
		this.ratingPoints = ratingPoints;
	}

	public long getRatingId() {
		return ratingId;
	}

	public void setRatingId(long ratingId) {
		this.ratingId = ratingId;
	}

	public BookingEntity getBookingId() {
		return bookingId;
	}

	public void setBookingId(BookingEntity bookingId) {
		this.bookingId = bookingId;
	}

	public String getRatingDescription() {
		return ratingDescription;
	}

	public void setRatingDescription(String ratingDescription) {
		this.ratingDescription = ratingDescription;
	}

	public long getRatingPoints() {
		return ratingPoints;
	}

	public void setRatingPoints(long ratingPoints) {
		this.ratingPoints = ratingPoints;
	}

}