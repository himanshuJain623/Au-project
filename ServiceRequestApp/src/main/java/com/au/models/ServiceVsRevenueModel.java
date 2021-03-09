package com.au.models;

public class ServiceVsRevenueModel {
	private String serviceName;
	private long serviceRevenue;

	public ServiceVsRevenueModel() {
		super();
	}

	public ServiceVsRevenueModel(String serviceName, long serviceRevenue) {
		super();
		this.serviceName = serviceName;
		this.serviceRevenue = serviceRevenue;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public long getServiceRevenue() {
		return serviceRevenue;
	}

	public void setServiceRevenue(long serviceRevenue) {
		this.serviceRevenue = serviceRevenue;
	}

}
