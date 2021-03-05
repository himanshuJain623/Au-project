package com.au.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "SERVICES_BY_PROVIDER")
public class ServiceProviderEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int serviceId;

//    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int providerId;
    
    @NotBlank
	@Column(name = "service_description")
	private String serviceDescription;
    
    @NotBlank
	@NumberFormat
	@Range(min=1, max=100)
    @Column(name="discount")
    private String discount;
    
    @NotBlank
	@NumberFormat
    @Column(name="service_price")
    private Long price;

	public ServiceProviderEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getProviderId() {
		return providerId;
	}

	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ServiceProviderEntity [serviceId=" + serviceId + ", providerId=" + providerId + ", serviceDescription="
				+ serviceDescription + ", discount=" + discount + ", price=" + price + "]";
	}
    
    

}
