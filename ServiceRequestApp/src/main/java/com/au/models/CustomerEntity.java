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
@Table(name = "CUSTOMER_DETAILS")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerId;
	
	@NotBlank
	@Column(name = "customer_name")
	private String customerName;
	
	@NotBlank
	@Column(name = "password")
	private String password;
	
	@NotBlank
	@Column(name = "customer_email")
	private String customerEmail;
	
	@NotBlank
	@NumberFormat
    @Length(min =10,max =10)
    @Column(name="customer_phone")
    private String customerPhone;
	
	
	@NotBlank
	@Column(name = "customer_location")
	private String customerLocation;
	
	
	public CustomerEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
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
