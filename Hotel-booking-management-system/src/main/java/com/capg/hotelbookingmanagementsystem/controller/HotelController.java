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

import com.capg.hotelbookingmanagementsystem.entity.Hotel;
import com.capg.hotelbookingmanagementsystem.exception.RecordNotFoundException;
import com.capg.hotelbookingmanagementsystem.repository.HotelRepository;
import com.capg.hotelbookingmanagementsystem.service.HotelServiceImpl;


@RestController
@RequestMapping("/Hotel")
public class HotelController {
	
	@Autowired
	private HotelServiceImpl hotelServiceImpl;
	
	@Autowired
	private HotelRepository hotelRepository;
    
	@PostMapping("/createHotel")
	public ResponseEntity<Hotel>createHotel(@RequestBody Hotel hotel){
		Hotel h= hotelServiceImpl.addHotel(hotel);
		return new ResponseEntity<Hotel>(h,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateHotel")
	public ResponseEntity<Hotel>updateHotel(@RequestBody Hotel hotel) throws RecordNotFoundException{
		Integer id=hotel.getHotelId();
		if(hotelRepository.findById(id).isPresent()) {
			return new ResponseEntity<Hotel>(hotelServiceImpl.updateHotel(hotel),HttpStatus.CREATED);
		}
		else {
			throw new RecordNotFoundException("Hotel with id"+id+"not found as per your request");
		}
		}
	
	@GetMapping("/ListHotel")
	public List<Hotel>getAllHotels(){
		return hotelServiceImpl.showAllHotels();
	}
	
	@DeleteMapping("/deleteById/{hotel_id}")
	public ResponseEntity<String> deleteHotelById(@PathVariable int hotel_id) throws RecordNotFoundException{
		if(hotelRepository.findById(hotel_id).isPresent()) {
			String hotel=hotelServiceImpl.removeHotelById(hotel_id);
			return new ResponseEntity<String>(hotel,HttpStatus.OK);
		}
		else {
			throw new RecordNotFoundException("Hotel with id"+hotel_id+"not found as per your request");
		}
	}
	
	@GetMapping("/getById/{hotel_id}")
	public ResponseEntity<Hotel> gethotelById(@PathVariable int hotel_id) throws RecordNotFoundException{
		if(hotelRepository.findById(hotel_id).isPresent()) {
			Hotel hotel=hotelServiceImpl.showHotelById(hotel_id);
			return new ResponseEntity<Hotel>(hotel,HttpStatus.OK);
		}
		else {
			throw new RecordNotFoundException("Hotel with id"+hotel_id+"not found as per your request");
		}
	}
}
