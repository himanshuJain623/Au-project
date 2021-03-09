package com.au.models;

public class ServiceVsCustomerModel {
	private String serviceName;
	private long numberOfCustomer;

	public ServiceVsCustomerModel() {
		super();
	}

	public ServiceVsCustomerModel(String serviceName, int numberOfCustomer) {
		super();
		this.serviceName = serviceName;
		this.numberOfCustomer = numberOfCustomer;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public long getNumberOfCustomer() {
		return numberOfCustomer;
	}

	public void setNumberOfCustomer(long numberOfCustomer) {
		this.numberOfCustomer = numberOfCustomer;
	}

}
