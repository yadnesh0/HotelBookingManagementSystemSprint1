package com.capg.hotelbookingmanagementsystem.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false)
	private int hotelId;
	private String city;
	@Column(unique = true, updatable = false)
	private String hotelName;
	private String address;
	private String description;
	private double avgRatePerDay;
	private String email;
	private String phone1;
	private String phone2;
	private String website;
	@OneToOne(mappedBy = "hotel",cascade = CascadeType.ALL)
	private BookingDetails booking;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "hotel",cascade = CascadeType.ALL)
	@JsonIgnoreProperties("hotel")
	private List<RoomDetails> rooms;

	public Hotel(int hotel_id) {
		this.hotelId = hotel_id;
	}
	
	
	

}
