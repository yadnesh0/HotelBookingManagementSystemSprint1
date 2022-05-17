package com.capg.hotelbookingmanagementsystem.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int hotel_id;
	private String city;
	private String hotel_name;
	private String address;
	private String description;
	private double avg_rate_per_day;
	private String email;
	private String phone1;
	private String phone2;
	private String website;
	@OneToOne(mappedBy = "hotel",cascade = CascadeType.ALL)
	private BookingDetails bDetails;
	
	@OneToMany(mappedBy = "hotel2",cascade = CascadeType.ALL)
	private List<RoomDetails> roomDetails;
	
	

}
