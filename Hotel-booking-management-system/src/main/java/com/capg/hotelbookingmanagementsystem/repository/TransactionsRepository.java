package com.capg.hotelbookingmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.hotelbookingmanagementsystem.entity.Transactions;


@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {

}
