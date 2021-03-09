package com.au.models;

import java.util.List;

public class ProviderDashboardModel {
	private float averageRating;
	private long totalRevenue;
	private long totalBookings;

	private List<ServiceVsCustomerModel> providerServiceCustomers;
	private List<ServiceVsBookingModel> providerServiceBookings;
	private List<ServiceVsRevenueModel> providerServiceRevenue;

	public ProviderDashboardModel() {
		super();
	}

	public ProviderDashboardModel(float averageRating, long totalRevenue, long totalBookings,
			List<ServiceVsCustomerModel> providerServiceCustomers, List<ServiceVsBookingModel> providerServiceBookings,
			List<ServiceVsRevenueModel> providerServiceRevenue) {
		super();
		this.averageRating = averageRating;
		this.totalRevenue = totalRevenue;
		this.totalBookings = totalBookings;
		this.providerServiceCustomers = providerServiceCustomers;
		this.providerServiceBookings = providerServiceBookings;
		this.providerServiceRevenue = providerServiceRevenue;
	}

	public float getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(float averageRating) {
		this.averageRating = averageRating;
	}

	public long getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(long totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public long getTotalBookings() {
		return totalBookings;
	}

	public void setTotalBookings(long totalBookings) {
		this.totalBookings = totalBookings;
	}

	public List<ServiceVsCustomerModel> getProviderServiceCustomers() {
		return providerServiceCustomers;
	}

	public void setProviderServiceCustomers(List<ServiceVsCustomerModel> providerServiceCustomers) {
		this.providerServiceCustomers = providerServiceCustomers;
	}

	public List<ServiceVsBookingModel> getProviderServiceBookings() {
		return providerServiceBookings;
	}

	public void setProviderServiceBookings(List<ServiceVsBookingModel> providerServiceBookings) {
		this.providerServiceBookings = providerServiceBookings;
	}

	public List<ServiceVsRevenueModel> getProviderServiceRevenue() {
		return providerServiceRevenue;
	}

	public void setProviderServiceRevenue(List<ServiceVsRevenueModel> providerServiceRevenue) {
		this.providerServiceRevenue = providerServiceRevenue;
	}

}
