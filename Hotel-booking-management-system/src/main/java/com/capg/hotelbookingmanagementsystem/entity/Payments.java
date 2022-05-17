package com.capg.hotelbookingmanagementsystem.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payments {
 @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private int payment_id;
 
 @OneToOne(cascade = CascadeType.ALL)
 @JoinColumn(name="transaction_id")
 private Transactions transactions;
 
 @ManyToOne(cascade = CascadeType.ALL)
 @JoinColumn(name="booking_id")
 private BookingDetails booking;
 
 
 
 
}
