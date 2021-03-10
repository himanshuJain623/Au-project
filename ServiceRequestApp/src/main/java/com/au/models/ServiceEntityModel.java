package com.au.models;

public class ServiceEntityModel {

	private String nameOfService;

	public ServiceEntityModel(String nameOfService) {
		super();
		this.nameOfService = nameOfService;
	}

	public String getNameOfService() {
		return nameOfService;
	}

	public void setNameOfService(String nameOfService) {
		this.nameOfService = nameOfService;
	}

}