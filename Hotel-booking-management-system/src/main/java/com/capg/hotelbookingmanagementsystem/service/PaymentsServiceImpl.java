package com.capg.hotelbookingmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.hotelbookingmanagementsystem.entity.BookingDetails;
import com.capg.hotelbookingmanagementsystem.entity.Payments;
import com.capg.hotelbookingmanagementsystem.repository.BookingDetailsRepository;
import com.capg.hotelbookingmanagementsystem.repository.PaymentsRepository;
@Service
public class PaymentsServiceImpl implements PaymentsService {

	@Autowired
	private PaymentsRepository paymentsRepository;
	@Autowired
	private BookingDetailsRepository bookingDetailsRepository;
	@Override
	public Payments addPayments(Payments payments) {
		Payments  op  = payments;
		Integer id = op.getBooking().getBookingId();
		BookingDetails bd = bookingDetailsRepository.findByBookingId(id);
		op.setBooking(bd);
		paymentsRepository.save(op);
		return op;
	}

}
