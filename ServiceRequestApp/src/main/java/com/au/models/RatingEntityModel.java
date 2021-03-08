package com.au.models;

public class RatingEntityModel {

	private long bookingId;
	private String ratingDescription;
	private long ratingPoints;

	public RatingEntityModel() {
		super();
	}

	public RatingEntityModel(long bookingId, String ratingDescription, long ratingPoints) {
		super();
		this.bookingId = bookingId;
		this.ratingDescription = ratingDescription;
		this.ratingPoints = ratingPoints;
	}

	public long getBookingId() {
		return bookingId;
	}

	public void setBookingId(long bookingId) {
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