package com.capg.hotelbookingmanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.hotelbookingmanagementsystem.entity.Hotel;
import com.capg.hotelbookingmanagementsystem.repository.HotelRepository;
@Service
public class HotelServiceImpl implements HotelService {
	@Autowired
	private HotelRepository hotelRepository;

	@Override
	public Hotel addHotel(Hotel hotel) {	
		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel updateHotel(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	@Override
	public String removeHotelById(int hotel_id) { 
		hotelRepository.deleteById(hotel_id);
		 return "Hotel deleted";
	}

	@Override
	public List<Hotel> showAllHotels() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel showHotelById(int hotel_id) {
		
		return hotelRepository.findById(hotel_id).get();
	}

}
