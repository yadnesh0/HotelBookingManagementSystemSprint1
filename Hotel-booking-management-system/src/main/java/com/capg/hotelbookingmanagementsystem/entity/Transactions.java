package com.capg.hotelbookingmanagementsystem.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO )
	private int transaction_id;
	private double amount;
	@OneToOne(mappedBy = "transactions",cascade = CascadeType.ALL)
	private Payments payments;
	
}
