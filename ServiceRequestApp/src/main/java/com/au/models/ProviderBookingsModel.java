package com.au.models;

import java.sql.Date;

public class ProviderBookingsModel {
	private Long bookingId;
	private String providerName;
	private String customerName;
	private String serviceName;
	private String bookingStatus;
	private Date bookingDate;
	private Long bookingCost;

	private String ratingDescription;
	private long ratingPoints;

	public ProviderBookingsModel() {
		super();
	}

	public ProviderBookingsModel(Long bookingId, String providerName, String customerName, String serviceName,
			String bookingStatus, Date bookingDate, Long bookingCost) {
		super();
		this.bookingId = bookingId;
		this.providerName = providerName;
		this.customerName = customerName;
		this.serviceName = serviceName;
		this.bookingStatus = bookingStatus;
		this.bookingDate = bookingDate;
		this.bookingCost = bookingCost;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Long getBookingCost() {
		return bookingCost;
	}

	public void setBookingCost(Long bookingCost) {
		this.bookingCost = bookingCost;
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
