package com.capg.hotelbookingmanagementsystem.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int bookingId;
	private Date bookedFrom;
	private Date bookedTo;
	private int noOfAdults;
	private int noOfChildren;
	private double amount;
	@OneToOne(targetEntity = User.class,cascade =  CascadeType.ALL)
	@JoinColumn(name="user_id")
    private User users;
	
	@OneToOne(targetEntity = Hotel.class,cascade = CascadeType.ALL)
	@JoinColumn(name="hotel_id")
	private Hotel hotel;
	
	@OneToMany(targetEntity = RoomDetails.class,cascade = CascadeType.ALL)
	private List<RoomDetails> rooms;
	
	@OneToMany(mappedBy = "booking",cascade = CascadeType.ALL)
	private List<Payments> payments;
}