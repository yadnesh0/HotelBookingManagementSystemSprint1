package com.capg.hotelbookingmanagementsystem.service;

import java.util.List;
import com.capg.hotelbookingmanagementsystem.entity.BookingDetails;
import com.capg.hotelbookingmanagementsystem.exception.RoomNotAvailableException;

public interface BookingDetailsService {
	BookingDetails addBookingDetails(BookingDetails bookingDetails) throws RoomNotAvailableException;
	BookingDetails updateBookingDetails(BookingDetails bookingDetails);
	String removeBookingDetailsByBookingId(int bookingID );
	List<BookingDetails> showAllBookingDetails();
	BookingDetails showBookingDetailsByBookingId(int bookingID);
}
