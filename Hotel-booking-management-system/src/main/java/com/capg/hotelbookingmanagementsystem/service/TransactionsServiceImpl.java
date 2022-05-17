package com.capg.hotelbookingmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.hotelbookingmanagementsystem.entity.Transactions;
import com.capg.hotelbookingmanagementsystem.repository.TransactionsRepository;

@Service
public class TransactionsServiceImpl implements TransactionsService {
     @Autowired
	private TransactionsRepository transactionsrepository;
	
     @Override
	public Transactions addTransactions(Transactions transactions) {
		
    	 //Transactions t=transactionsrepository.save(transactions);
    
    	 return transactionsrepository.save(transactions);
    	 
		
	}
   
	
	
}
