package com.capg.hotelbookingmanagementsystem.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private int bookingId;
	@Column(name = "booked_from")
	private Date bookedFrom;
	@Column(name = "booked_to")
	private Date bookedTo;
	@Column(name = "no_of_adults")
	private int noOfAdults;
	@Column(name = "no_of_childern")
	private int noOfChildren;
	private double amount;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
    private User users;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="hotel_id")
	private Hotel hotel;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<RoomDetails> roomDetails;
	
	@OneToMany(mappedBy = "booking")
	private List<Payments> payments;
}