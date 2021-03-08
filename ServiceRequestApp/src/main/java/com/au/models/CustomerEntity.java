package com.au.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "CUSTOMER_DETAILS")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long customerId;

	@NotBlank
	@Column(name = "customer_name")
	private String customerName;

	@NotBlank
	@Column(name = "password")
	private String password;

	@NotBlank
	@Column(name = "customer_email", unique = true)
	private String customerEmail;

	@NotBlank
	@NumberFormat
	@Length(min = 10, max = 10)
	@Column(name = "customer_phone")
	private String customerPhone;

	@NotBlank
	@Column(name = "customer_location")
	private String customerLocation;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerId", cascade = CascadeType.ALL)
	private List<BookingEntity> bookingList = new ArrayList<>();

	public CustomerEntity() {
		super();
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerLocation() {
		return customerLocation;
	}

	public void setCustomerLocation(String customerLocation) {
		this.customerLocation = customerLocation;
	}

	@Override
	public String toString() {
		return "CustomerEntity [customerId=" + customerId + ", customerName=" + customerName + ", password=" + password
				+ ", customerEmail=" + customerEmail + ", customerPhone=" + customerPhone + ", customerLocation="
				+ customerLocation + "]";
	}

}