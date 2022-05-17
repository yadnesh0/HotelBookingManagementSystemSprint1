package com.capg.hotelbookingmanagementsystem.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.hotelbookingmanagementsystem.entity.Admin;
import com.capg.hotelbookingmanagementsystem.entity.BookingDetails;
import com.capg.hotelbookingmanagementsystem.entity.Hotel;
import com.capg.hotelbookingmanagementsystem.entity.RoomDetails;
import com.capg.hotelbookingmanagementsystem.entity.User;
import com.capg.hotelbookingmanagementsystem.service.AdminServiceImpl;
import com.capg.hotelbookingmanagementsystem.service.BookingDetailsServiceImpl;
import com.capg.hotelbookingmanagementsystem.service.HotelServiceImpl;
import com.capg.hotelbookingmanagementsystem.service.RoomDetailsServiceImpl;
import com.capg.hotelbookingmanagementsystem.service.UserServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {
	private int validAdmin = 0; //flag variable
	
	@Autowired
	private AdminServiceImpl adminservice;
	@Autowired
	private BookingDetailsServiceImpl bookingservice;
	@Autowired
	private RoomDetailsServiceImpl roomservice;
	@Autowired
	private HotelServiceImpl hotelservice;
	@Autowired
	private UserServiceImpl userservice;

	
   /*..........................For Admin..................................*/
	
	@GetMapping("/login/{username}/{password}")
	public ResponseEntity<?> loginAdmin(@PathVariable("username") String username, @PathVariable("password") String password) {
		if(adminservice.validateAdmin(username, password) == true) {
			validAdmin = 1;
			Admin admin = adminservice.viewByAdminUserName(username, password).get();
			String welcome = "Welcome \n........................\n";
			return ResponseEntity.ok(welcome + "Id : " + admin.getAdminId() + "\nUsername : " + admin.getAdminUsername());
		} else
			return ResponseEntity.ok("Invalid credentials");
	}
	
	@PostMapping("/add")
	public Admin addAdmin(@Validated @RequestBody Admin admin) {
		return adminservice.addAdmin(admin);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(){
		if(validAdmin == 1) {
			validAdmin = 0;
			return ResponseEntity.ok("Logged out...");		
		}else
			return ResponseEntity.ok("Not Logged In");
	}
	
	
	/*..........................For User.............................*/
	
	@GetMapping("/user")
	public ResponseEntity<?> viewAllUsers(){
		if(validAdmin == 1) {
			return ResponseEntity.ok(userservice.showAllUsers());
		}else
			return ResponseEntity.ok("Not Logged In");
	}
	@PatchMapping("/user/{id}")
	public ResponseEntity<?> updateUserById(@PathVariable("id") Integer user_id,@Validated @RequestBody Map<Object, Object> fields){
		if(validAdmin == 1) {
			User user = userservice.showUserById(user_id);
			if(user != null) {
		    	  fields.forEach((key,value) -> {
					Field field = ReflectionUtils.findField(User.class, (String)key);
		    		  field.setAccessible(true);
		    		  ReflectionUtils.setField(field, user, value);
		    	  });	    
			 }return ResponseEntity.ok(userservice.updateUser(user));
		}else
		return ResponseEntity.ok("Not Logged In");
		
	}
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Integer user_id) {
		if(validAdmin == 1) {
			return ResponseEntity.ok(userservice.removeUserById(user_id));
		}else
		return ResponseEntity.ok("Not Logged In");
	}
	@GetMapping("/user/{id}")
	public ResponseEntity<?> fetchBookingDetails(@PathVariable("id") Integer user_id){
		if(validAdmin == 1) {
			User user = userservice.showUserById(user_id);		
		    BookingDetails booking = user.getBDetails();
		    return ResponseEntity.ok(booking); 
		}else
			return ResponseEntity.ok("Not Logged In");
	}
	
	
	/*..........................For Hotel................................*/
	
	@PostMapping("/hotel")
	public ResponseEntity<?> addHotel(@RequestBody Hotel h) {
		if(validAdmin == 1) {
			return ResponseEntity.ok(hotelservice.addHotel(h));
		}else
		return ResponseEntity.ok("Not Logged In");
	}

	@GetMapping("/hotel")
	public ResponseEntity<?> viewAllHotels(){
		if(validAdmin == 1) {
			return ResponseEntity.ok(hotelservice.showAllHotels());
		}else
			return ResponseEntity.ok("Not Logged In");
	}
	@PatchMapping("/hotel/{id}")
	public ResponseEntity<?> updateHotelById(@PathVariable("id") Integer hotel_id,@Validated @RequestBody Map<Object, Object> fields){
		if(validAdmin == 1) {
			Hotel hotel = hotelservice.showHotelById(hotel_id);
			if(hotel != null) {
		    	  fields.forEach((key,value) -> {
					Field field = ReflectionUtils.findField(Hotel.class, (String)key);
		    		  field.setAccessible(true);
		    		  ReflectionUtils.setField(field, hotel, value);
		    	  });	    
			 }return ResponseEntity.ok(hotelservice.updateHotel(hotel));
		}else
		return ResponseEntity.ok("Not Logged In");
		
	}
	@DeleteMapping("/hotel/{id}")
	public ResponseEntity<?> deleteHotel(@PathVariable("id") Integer hotel_id) {
		if(validAdmin == 1) {
			return ResponseEntity.ok(hotelservice.removeHotelById(hotel_id));
		}else
		return ResponseEntity.ok("Not Logged In");
	}
	@GetMapping("/hotel/{id}")
	public ResponseEntity<?> fetchRoomDetails(@PathVariable("id") Integer hotel_id){
		if(validAdmin == 1) {
			Hotel hotel = hotelservice.showHotelById(hotel_id);		
		    List<RoomDetails> rooms = hotel.getRoomDetails();
		    List<String> roomlist = new ArrayList<>();
		    int i = 1;
		    for(RoomDetails obj : rooms) {
		    	roomlist.add( "Serial : " + i +
		    			 	   ", Room id : " + obj.getRoom_id() + 
                               ", Room No : " + obj.getRoom_no() + 
                               ", Room Type : " + obj.getRoom_type() + 
                               ", Rate per Day : " + obj.getRate_per_day() + 
                               ", Is Available : " + obj.isIsavailable());
		    	i++;
		    }
		    return ResponseEntity.ok(roomlist); 
		}else
			return ResponseEntity.ok("Not Logged In");
	}
	
	
	/*..........................For Room................................*/
	
	@PostMapping("/room")
	public ResponseEntity<?> addRoomDetails(@RequestBody RoomDetails r) {
		if(validAdmin == 1) {
			return ResponseEntity.ok(roomservice.addRoomDetails(r));
		}else
		return ResponseEntity.ok("Not Logged In");
	}
	@GetMapping("/room")
	public ResponseEntity<?> viewAllRooms(){
		if(validAdmin == 1) {
			return ResponseEntity.ok(roomservice.showAllRoomDetails());
		}else
			return ResponseEntity.ok("Not Logged In");
	}
	@PatchMapping("/room/{id}")
	public ResponseEntity<?> updateRoomDetailsById(@PathVariable("id") Integer room_id,@Validated @RequestBody Map<Object, Object> fields){
		if(validAdmin == 1) {
			RoomDetails room = roomservice.showRoomDetailsById(room_id);
			if(room != null) {
		    	  fields.forEach((key,value) -> {
					Field field = ReflectionUtils.findField(RoomDetails.class, (String)key);
		    		  field.setAccessible(true);
		    		  ReflectionUtils.setField(field, room, value);
		    	  });	    
			 }return ResponseEntity.ok(roomservice.updateRoomDetails(room));
		}else
		return ResponseEntity.ok("Not Logged In");
		
	}
	@DeleteMapping("/room/{id}")
	public ResponseEntity<?> deleteRoomDetails(@PathVariable("id") Integer room_id) {
		if(validAdmin == 1) {
			return ResponseEntity.ok(roomservice.removeRoomDetailsById(room_id));
		}else
		return ResponseEntity.ok("Not Logged In");
	}

	/*..........................For Booking................................*/
	@GetMapping("/booking")
	public ResponseEntity<?> viewAllBookingDetails(){
		if(validAdmin == 1) {
			return ResponseEntity.ok(bookingservice.showAllBookingDetails());
		}else
			return ResponseEntity.ok("Not Logged In");
	}
	@PatchMapping("/booking/{id}")
	public ResponseEntity<?> updateBookingDetailsById(@PathVariable("id") Integer booking_id,@Validated @RequestBody Map<Object, Object> fields){
		if(validAdmin == 1) {
			BookingDetails booking = bookingservice.showBookingDetailsByBookingId(booking_id);
			if(booking != null) {
		    	  fields.forEach((key,value) -> {
					Field field = ReflectionUtils.findField(BookingDetails.class, (String)key);
		    		  field.setAccessible(true);
		    		  ReflectionUtils.setField(field, booking, value);
		    	  });	    
			 }return ResponseEntity.ok(bookingservice.updateBookingDetails(booking));
		}else
		return ResponseEntity.ok("Not Logged In");
	}
	@DeleteMapping("/booking/{id}")
	public ResponseEntity<?> deleteBookingDetails(@PathVariable("id") Integer booking_id) {
		if(validAdmin == 1) {
			return ResponseEntity.ok(bookingservice.removeBookingDetailsByBookingId(booking_id));
		}else
		return ResponseEntity.ok("Not Logged In");
	}
	@GetMapping("/booking/{id}")
	public ResponseEntity<?> fetchUserByBooking(@PathVariable("id") Integer booking_id){
		if(validAdmin == 1) {
			BookingDetails bDetails = bookingservice.showBookingDetailsByBookingId(booking_id);		
		    User user = bDetails.getUsers();
		    return ResponseEntity.ok(user); 
		}else
			return ResponseEntity.ok("Not Logged In");
	}

}


