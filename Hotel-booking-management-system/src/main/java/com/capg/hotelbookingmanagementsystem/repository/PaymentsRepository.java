package com.capg.hotelbookingmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capg.hotelbookingmanagementsystem.entity.Payments;

public interface PaymentsRepository extends JpaRepository<Payments,Integer> {

}
