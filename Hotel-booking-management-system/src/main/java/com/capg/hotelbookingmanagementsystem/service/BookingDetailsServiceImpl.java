package com.capg.hotelbookingmanagementsystem.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capg.hotelbookingmanagementsystem.entity.BookingDetails;
import com.capg.hotelbookingmanagementsystem.entity.Hotel;
import com.capg.hotelbookingmanagementsystem.entity.Payments;
import com.capg.hotelbookingmanagementsystem.entity.RoomDetails;
import com.capg.hotelbookingmanagementsystem.entity.User;
import com.capg.hotelbookingmanagementsystem.exception.RoomNotAvailableException;
import com.capg.hotelbookingmanagementsystem.repository.BookingDetailsRepository;
import com.capg.hotelbookingmanagementsystem.repository.HotelRepository;
import com.capg.hotelbookingmanagementsystem.repository.PaymentsRepository;
import com.capg.hotelbookingmanagementsystem.repository.RoomDetailsRepository;
import com.capg.hotelbookingmanagementsystem.repository.UserRepository;

@Service
public class BookingDetailsServiceImpl implements BookingDetailsService{
	@Autowired
	private BookingDetailsRepository bookingDetailsRepository;
	@Autowired
	private RoomDetailsRepository roomDetailsRepository;
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PaymentsRepository paymentsRepository;
	
	@Override
	public BookingDetails addBookingDetails(BookingDetails bookingDetails) throws RoomNotAvailableException{
		BookingDetails booking = bookingDetails;
		
		if(hotelRepository.findById(booking.getHotel().getHotelId()).isPresent()) {
			
			List<RoomDetails> req = bookingDetails.getRooms(); 
			List<RoomDetails> avail,db;
			HashMap<Integer,Boolean> b = new HashMap<>();
			List<Integer> r = new ArrayList<>();
			
			/*..........................For Rooms.............................*/
			db = roomDetailsRepository.findAll();          
			avail = db.stream().filter((i)-> i.isAvailable()==true).collect(Collectors.toList());

			avail.forEach(i -> b.put(i.getRoomId(), i.isAvailable()));
			req.forEach(i -> r.add(i.getRoomId()));

			for (Integer j : r) {
				if(b.containsKey(j) && b.containsValue(true))
					continue;
				else
					throw new RoomNotAvailableException("Room Not Available"); 	
			}
		    List<RoomDetails> update = new ArrayList<RoomDetails>();
			r.forEach(k -> {
				             RoomDetails room = roomDetailsRepository.findById(k).get();
				             room.setAvailable(false);
				             update.add(room);
			         });
			booking.setRooms(update);
			/*..........................For Hotel.............................*/
			Hotel h =   hotelRepository.findByHotelId(booking.getHotel().getHotelId());
			booking.setHotel(h);
			/*..........................For User.............................*/
			User u = userRepository.findById(booking.getUsers().getUserId()).get();
			booking.setUsers(u);
			
			/*..........................For Payment.............................*/
			List<Payments> updateList = new ArrayList<Payments>();
			List<Payments> p = booking.getPayments();
			double sum = 0.0;
			for(Payments n : p) {
			   sum = sum + n.getTransactions().getAmount();
			   updateList.add(n);
			} 
			booking.setAmount(sum);
			booking.setPayments(updateList);
			Integer bId = bookingDetailsRepository.save(booking).getBookingId();
			BookingDetails bd = bookingDetailsRepository.findByBookingId(bId);
			List<Payments> pp =  bd.getPayments();
			Vector<Payments> pp1 = new Vector<>();
			pp1.addAll(pp);
			pp1.forEach(z -> { z.setTransactions(z.getTransactions());
		                   	  z.setPaymentId(z.getPaymentId());
		                   	  z.setBooking(bd);
		                   	  paymentsRepository.save(z);
			                });
			return booking;
		}
		throw new RoomNotAvailableException("Hotel Not Available");
	}

	@Override
	public BookingDetails updateBookingDetails(BookingDetails bookingDetails) {
		 Integer idetail = bookingDetails.getBookingId();
		 BookingDetails bdetail =  bookingDetailsRepository.findById(idetail).get();
		 List<RoomDetails> old = bdetail.getRooms();
		 BookingDetails booking = bookingDetails;
			
			if(hotelRepository.findById(booking.getHotel().getHotelId()).isPresent()) {
				
				List<RoomDetails> req = bookingDetails.getRooms(); 
				List<RoomDetails> avail,db;
				HashMap<Integer,Boolean> b = new HashMap<>();
				List<Integer> r = new ArrayList<>();
				
				/*..........................For Rooms.............................*/
				db = roomDetailsRepository.findAll();          
				avail = db.stream().filter((i)-> i.isAvailable()==true).collect(Collectors.toList());

				avail.forEach(i -> b.put(i.getRoomId(), i.isAvailable()));
				req.forEach(i -> r.add(i.getRoomId()));

				for (Integer j : r) {
					if(b.containsKey(j) && b.containsValue(true))
						continue;
					else
						throw new RoomNotAvailableException("Room Not Available"); 	
				}
				
			    List<RoomDetails> update = new ArrayList<RoomDetails>();
				r.forEach(k -> {
					             RoomDetails room = roomDetailsRepository.findById(k).get();
					             room.setAvailable(false);
					             update.add(room);
				         });
				booking.setRooms(update);
				old.forEach(i -> { i.setAvailable(true); roomDetailsRepository.save(i); });
				/*..........................For Hotel.............................*/
				Hotel h =   hotelRepository.findByHotelId(booking.getHotel().getHotelId());
				booking.setHotel(h);
				/*..........................For User.............................*/
				User u = userRepository.findById(booking.getUsers().getUserId()).get();
				booking.setUsers(u);
				
				/*..........................For Payment.............................*/
				List<Payments> updateList = new ArrayList<Payments>();
				List<Payments> p = booking.getPayments();
				double sum = 0.0;
				for(Payments n : p) {
				   sum = sum + n.getTransactions().getAmount();
				   updateList.add(n);
				} 
				booking.setAmount(sum);
				booking.setPayments(updateList);
				Integer bId = bookingDetailsRepository.save(booking).getBookingId();
				BookingDetails bd = bookingDetailsRepository.findByBookingId(bId);
				List<Payments> pp =  bd.getPayments();
				Vector<Payments> pp1 = new Vector<>();
				pp1.addAll(pp);
				pp1.forEach(z -> { z.setTransactions(z.getTransactions());
			                   	  z.setPaymentId(z.getPaymentId());
			                   	  z.setBooking(bd);
			                   	  paymentsRepository.save(z);
				                });
				return booking;
			}
			throw new RoomNotAvailableException("Hotel Not Available");
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