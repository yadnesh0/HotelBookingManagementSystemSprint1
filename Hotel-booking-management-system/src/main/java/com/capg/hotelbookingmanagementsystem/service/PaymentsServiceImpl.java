package com.capg.hotelbookingmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.hotelbookingmanagementsystem.entity.Payments;
import com.capg.hotelbookingmanagementsystem.repository.PaymentsRepository;
@Service
public class PaymentsServiceImpl implements PaymentsService {

	@Autowired
	private PaymentsRepository paymentsRepository;
	
	@Override
	public Payments addPayments(Payments payments) {
	
		return paymentsRepository.save(payments);
	}

}
