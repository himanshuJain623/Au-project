package com.au.models;

public class UserTypeDetailEntity {

	private String userType;
	private long userId;
	private String userName;
	private String userEmail;
	private String userPhone;
	private String userLocation;

	public UserTypeDetailEntity() {
		super();
	}

	public UserTypeDetailEntity(String userType, long userId, String userName, String userEmail, String userPhone,
			String userLocation) {
		super();
		this.userType = userType;
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.userLocation = userLocation;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long i) {
		this.userId = i;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserLocation() {
		return userLocation;
	}

	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}

}
