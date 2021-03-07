package com.au.models;

import java.sql.Date;

public class CustomerBookingsEntity {

	private String providerName;
	private String serviceName;
	private String bookingStatus;
	private Date bookingDate;
	private long bookingCost;

	public CustomerBookingsEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerBookingsEntity(String providerName, String serviceName, String bookingStatus, Date bookingDate,
			long bookingCost) {
		super();
		this.providerName = providerName;
		this.serviceName = serviceName;
		this.bookingStatus = bookingStatus;
		this.bookingDate = bookingDate;
		this.bookingCost = bookingCost;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
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

	public long getBookingCost() {
		return bookingCost;
	}

	public void setBookingCost(long bookingCost) {
		this.bookingCost = bookingCost;
	}

}