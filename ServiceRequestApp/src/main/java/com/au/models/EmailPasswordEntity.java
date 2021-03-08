package com.au.models;

public class EmailPasswordEntity {

	private String userMail;
	private String userPassword;

	public EmailPasswordEntity() {
		super();
	}

	public EmailPasswordEntity(String userMail, String userPassword) {
		super();
		this.userMail = userMail;
		this.userPassword = userPassword;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}
