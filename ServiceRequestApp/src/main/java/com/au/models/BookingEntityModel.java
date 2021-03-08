package com.au.models;

public class BookingEntityModel {
	private String spId;
	private Long customerId;

	public BookingEntityModel() {
		super();
	}

	public BookingEntityModel(String spId, Long customerId) {
		super();
		this.spId = spId;
		this.customerId = customerId;
	}

	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

}
