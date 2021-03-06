package com.au.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "SERVICES_BY_PROVIDER")
public class ServiceProviderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long sidId;

	@ManyToOne
	@JoinColumn(name = "serviceId", referencedColumnName = "serviceId")
	private ServiceEntity serviceId;

	@ManyToOne
	@JoinColumn(name = "providerId", referencedColumnName = "providerId")
	private ProviderEntity providerId;

	@NotBlank
	@Column(name = "service_description")
	private String serviceDescription;

	@NotNull
	@NumberFormat
//	@Range(min = 1, max = 100)
	@Column(name = "discount")
	private int discount;

	@NotNull
	@NumberFormat
	@Column(name = "service_price")
	private int price;

	public ServiceProviderEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceProviderEntity(Long sidId, ServiceEntity serviceId, ProviderEntity providerId,
			@NotBlank String serviceDescription,
			@NotBlank @Range(min = 1, max = 100) @NotBlank @Range(min = 1, max = 100) int discount,
			@NotBlank @NotBlank int price) {
		super();
		this.sidId = sidId;
		this.serviceId = serviceId;
		this.providerId = providerId;
		this.serviceDescription = serviceDescription;
		this.discount = discount;
		this.price = price;
	}

	public Long getSidId() {
		return sidId;
	}

	public void setSidId(Long sidId) {
		this.sidId = sidId;
	}

	public ServiceEntity getServiceId() {
		return serviceId;
	}

	public void setServiceId(ServiceEntity serviceId) {
		this.serviceId = serviceId;
	}

	public ProviderEntity getProviderId() {
		return providerId;
	}

	public void setProviderId(ProviderEntity providerId) {
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

	public void setDiscount(int i) {
		this.discount = i;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int i) {
		this.price = i;
	}

}
