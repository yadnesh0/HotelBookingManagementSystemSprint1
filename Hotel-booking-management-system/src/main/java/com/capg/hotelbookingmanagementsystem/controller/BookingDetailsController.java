package com.capg.hotelbookingmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.hotelbookingmanagementsystem.entity.BookingDetails;
import com.capg.hotelbookingmanagementsystem.exception.RecordNotFoundException;
import com.capg.hotelbookingmanagementsystem.repository.BookingDetailsRepository;
import com.capg.hotelbookingmanagementsystem.service.BookingDetailsServiceImpl;


@RestController
@RequestMapping("/bookingDetails")
public class BookingDetailsController {
	
	@Autowired
	private BookingDetailsServiceImpl bookingDetailsService;
	@Autowired
	private BookingDetailsRepository bookingDetailsRepository;
	
	@PostMapping("/createBookingDetails")
	public ResponseEntity<BookingDetails> createBookingDetails(@RequestBody BookingDetails bookingDetails) throws RecordNotFoundException{
		BookingDetails bd = bookingDetailsService.addBookingDetails(bookingDetails);
		return new ResponseEntity<BookingDetails>(bd,HttpStatus.CREATED);
	}
	@PutMapping("/updateBookingDetails")
	public ResponseEntity<BookingDetails> updateBookingDetails(@RequestBody BookingDetails bookingDetails) throws RecordNotFoundException{
		Integer id = bookingDetails.getBookingId();
	    if(bookingDetailsRepository.findById(id).isPresent()) {
	    	BookingDetails bd = bookingDetailsService.updateBookingDetails(bookingDetails);
	    return new ResponseEntity<BookingDetails>(bd,HttpStatus.OK);
	    }
	    else {
	    	throw new RecordNotFoundException("Booking Details with Id: "+id+" not found!!");
	    }
	}
	@DeleteMapping("/deleteById/{booking_id}")
	public ResponseEntity<String> deleteBookingDetails(@PathVariable int booking_id) throws RecordNotFoundException{
	    if(bookingDetailsRepository.findById(booking_id).isPresent()) {
	    	String bd = bookingDetailsService.removeBookingDetailsByBookingId(booking_id);
	    return new ResponseEntity<String>(bd,HttpStatus.OK);
	    }
	    else {
	    	throw new RecordNotFoundException("Booking Details with Id: "+booking_id+" not found!!");
	    }
	}
	@GetMapping("/getById/{booking_id}")
	public ResponseEntity<BookingDetails> getBookingDetails(@PathVariable int booking_id) throws RecordNotFoundException{
	    if(bookingDetailsRepository.findById(booking_id).isPresent()) {
	    	BookingDetails bd = bookingDetailsService.showBookingDetailsByBookingId(booking_id);
	    return new ResponseEntity<BookingDetails>(bd,HttpStatus.OK);
	    }
	    else {
	    	throw new RecordNotFoundException("Booking Details with Id: "+booking_id+" not found!!");
	    }
	}
	@GetMapping("/list")
	public List<BookingDetails> getAllBookingDetails(){
		return bookingDetailsService.showAllBookingDetails();
	}
}