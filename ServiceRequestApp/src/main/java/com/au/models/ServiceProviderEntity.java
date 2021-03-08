package com.au.models;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	private String spId;

	@ManyToOne
	@JoinColumn(name = "foreignServiceId", referencedColumnName = "serviceId")
	private ServiceEntity foreignServiceId;

	@ManyToOne
	@JoinColumn(name = "foreignProviderId", referencedColumnName = "providerId")
	private ProviderEntity foreignProviderId;

	@NotBlank
	@Column(name = "service_description")
	private String serviceDescription;

	@NotNull
	@NumberFormat
	@Range(min = 1, max = 100)
	@Column(name = "discount")
	private int discount;

	@NotNull
	@NumberFormat
	@Column(name = "service_price")
	private int price;

	public ServiceProviderEntity() {
		super();
	}

	public ServiceProviderEntity(ServiceEntity serviceId, ProviderEntity providerId,
			@NotBlank String serviceDescription,
			@NotBlank @Range(min = 1, max = 100) @NotBlank @Range(min = 1, max = 100) int discount,
			@NotBlank @NotBlank int price) {
		super();
		this.spId = serviceId.getServiceId() + "_" + providerId.getProviderId();
		this.foreignServiceId = serviceId;
		this.foreignProviderId = providerId;
		this.serviceDescription = serviceDescription;
		this.discount = discount;
		this.price = price;
	}

	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}

	public ServiceEntity getForeignServiceId() {
		return foreignServiceId;
	}

	public void setForeignServiceId(ServiceEntity foreignServiceId) {
		this.foreignServiceId = foreignServiceId;
	}

	public ProviderEntity getForeignProviderId() {
		return foreignProviderId;
	}

	public void setForeignProviderId(ProviderEntity foreignProviderId) {
		this.foreignProviderId = foreignProviderId;
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
