package com.au.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "PROVIDER_DETAILS")
public class ProviderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long providerId;

	@NotBlank
	@Column(name = "provider_name")
	private String providerName;

	@NotBlank
	@Column(name = "provider_email", unique = true)
	private String providerEmail;

	@NotBlank
	@Column(name = "password")
	private String password;

	@NotBlank
	@NumberFormat
	@Length(min = 10, max = 10)
	@Column(name = "provider_phone")
	private String providerPhone;

	@NotBlank
	@Column(name = "provider_location")
	private String providerLocation;

	public ProviderEntity() {
		super();
	}

	public ProviderEntity(Long providerId, @NotBlank String providerName, @NotBlank String password,
			@NotBlank String providerEmail, @NotBlank @Length(min = 10, max = 10) String providerPhone,
			@NotBlank String providerLocation) {
		super();
		this.providerId = providerId;
		this.providerName = providerName;
		this.password = password;
		this.providerEmail = providerEmail;
		this.providerPhone = providerPhone;
		this.providerLocation = providerLocation;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProviderEmail() {
		return providerEmail;
	}

	public void setProviderEmail(String providerEmail) {
		this.providerEmail = providerEmail;
	}

	public String getProviderPhone() {
		return providerPhone;
	}

	public void setProviderPhone(String providerPhone) {
		this.providerPhone = providerPhone;
	}

	public String getProviderLocation() {
		return providerLocation;
	}

	public void setProviderLocation(String providerLocation) {
		this.providerLocation = providerLocation;
	}
}