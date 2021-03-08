package com.au.models;

import java.sql.Date;

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

@Entity
@Table(name = "BOOKINGS_DETAILS")
public class BookingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long bookingId;

	@ManyToOne
	@JoinColumn(name = "fspId", referencedColumnName = "spId")
	private ServiceProviderEntity spId;

	@ManyToOne
	@JoinColumn(name = "customerId", referencedColumnName = "customerId")
	private CustomerEntity customerId;

	@NotBlank
	@Column(name = "booking_status", columnDefinition = "enum('Processing', 'Cancelled' ,'Accepted', 'Completed')")
	private String bookingStatus;

	@NotNull
	@Column(name = "booking_date")
	private java.sql.Date bookingDate;

	@NotNull
	@Column(name = "booking_cost")
	private long bookingCost;

	public BookingEntity() {
		super();
	}

	public BookingEntity(Long bookingId, CustomerEntity customerId, String bookingStatus, Date bookingDate,
			long bookingCost) {
		super();
		this.bookingId = bookingId;
		this.customerId = customerId;
		this.bookingStatus = bookingStatus;
		this.bookingDate = bookingDate;
		this.bookingCost = bookingCost;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public ServiceProviderEntity getSpId() {
		return spId;
	}

	public void setSpId(ServiceProviderEntity spId) {
		this.spId = spId;
	}

	public CustomerEntity getCustomerId() {
		return customerId;
	}

	public void setCustomerId(CustomerEntity customerId) {
		this.customerId = customerId;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public java.sql.Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate() {
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		this.bookingDate = date;
	}

	public long getBookingCost() {
		return bookingCost;
	}

	public void setBookingCost(long bookingCost) {
		this.bookingCost = bookingCost;
	}

}