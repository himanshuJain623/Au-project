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
import javax.validation.constraints.Size;

import com.au.annotations.UniqueServiceName;

@Entity
@Table(name = "SERVICE_DETAILS")
public class ServiceEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long serviceId;

//	@UniqueServiceName(message = "This service is already exists!")
	@NotBlank
	@Size(min = 3, message = "Name must be at least 3 character!")
	@Column(name = "service_name", unique = true)
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
