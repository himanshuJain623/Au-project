package com.au.models;

public class ServiceVsBookingModel {
	private String serviceName;
	private long numberOfBookings;

	public ServiceVsBookingModel() {
		super();
	}

	public ServiceVsBookingModel(String serviceName, int numberOfBookings) {
		super();
		this.serviceName = serviceName;
		this.numberOfBookings = numberOfBookings;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public long getNumberOfBookings() {
		return numberOfBookings;
	}

	public void setNumberOfBookings(long numberOfBookings) {
		this.numberOfBookings = numberOfBookings;
	}

}
