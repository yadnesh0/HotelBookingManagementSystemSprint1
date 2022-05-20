package com.capg.hotelbookingmanagementsystem.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RoomDetails {
    @Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
	private int roomId;
    @Column(unique = true, updatable = false)
	private String roomNo;
	private String roomType;
	private double ratePerDay;
	private boolean isAvailable;
		
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="hotel_id",referencedColumnName = "hotelId")
	@JsonIgnoreProperties("rooms")
	private Hotel hotel;

	public RoomDetails(int roomId) {
		this.roomId = roomId;
	}
}
	


