package com.au.models;

public class ServiceProviderEntityModel {
	private Long serviceId;
	private Long providerId;
	private String serviceDescription;
	private int discount;
	private int price;

	public ServiceProviderEntityModel() {
		super();
	}

	public ServiceProviderEntityModel(Long serviceId, Long providerId, String serviceDescription, int discount,
			int price) {
		super();
		this.serviceId = serviceId;
		this.providerId = providerId;
		this.serviceDescription = serviceDescription;
		this.discount = discount;
		this.price = price;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
