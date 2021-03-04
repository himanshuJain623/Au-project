package com.au.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "SERVICE_DETAILS")
public class ServiceEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long serviceId;

	@NotBlank
	@Column(name = "service_name")
	private String serviceName;

	public ServiceEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceEntity(Long serviceId, @NotBlank String serviceName) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Override
	public String toString() {
		return "Service [serviceId=" + serviceId + ", serviceName=" + serviceName + "]";
	}

}
