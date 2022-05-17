package com.capg.hotelbookingmanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capg.hotelbookingmanagementsystem.entity.BookingDetails;
import com.capg.hotelbookingmanagementsystem.repository.BookingDetailsRepository;

@Service
public class BookingDetailsServiceImpl implements BookingDetailsService{
	@Autowired
	private BookingDetailsRepository bookingDetailsRepository;
	@Override
	public BookingDetails addBookingDetails(BookingDetails bookingDetails){
	    return bookingDetailsRepository.save(bookingDetails);
	}

	@Override
	public BookingDetails updateBookingDetails(BookingDetails bookingDetails) {
		return bookingDetailsRepository.save(bookingDetails);
	}

	@Override
	public String removeBookingDetailsByBookingId(int bookingID) {
	    bookingDetailsRepository.deleteById(bookingID);
		return "Booking Deleted";
	}

	@Override
	public List<BookingDetails> showAllBookingDetails() {
		return bookingDetailsRepository.findAll();
	}

	@Override
	public BookingDetails showBookingDetailsByBookingId(int bookingID) {
		return bookingDetailsRepository.findById(bookingID).get();
	}
}