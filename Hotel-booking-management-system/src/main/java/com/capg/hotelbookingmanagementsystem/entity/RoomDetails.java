package com.capg.hotelbookingmanagementsystem.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RoomDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int room_id;
	private String room_no;
	private String room_type;
	private double rate_per_day;
	private boolean isavailable;
		
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="hotel_id")
	private Hotel hotel2;
	
}
	


