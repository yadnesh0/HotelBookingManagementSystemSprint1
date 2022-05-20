package com.capg.hotelbookingmanagementsystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.hotelbookingmanagementsystem.entity.Hotel;
import com.capg.hotelbookingmanagementsystem.entity.RoomDetails;
import com.capg.hotelbookingmanagementsystem.repository.HotelRepository;
import com.capg.hotelbookingmanagementsystem.repository.RoomDetailsRepository;

@Service
public class RoomDetailsServiceImpl implements RoomDetailsService {
	@Autowired
    private RoomDetailsRepository roomDetailsRepository;
	@Autowired
    private HotelRepository hotelRepository;
	@Override
	public RoomDetails addRoomDetails(RoomDetails roomDetails) {
		RoomDetails r = roomDetails;
	    int id = r.getHotel().getHotelId();
	    Hotel h = hotelRepository.findByHotelId(id);
		r.setHotel(h);
		return roomDetailsRepository.save(r);
	}

	@Override
	public RoomDetails updateRoomDetails(RoomDetails roomDetails) {	
		RoomDetails r = roomDetails;
	    int id = r.getHotel().getHotelId();
	    Hotel h = hotelRepository.findByHotelId(id);
		r.setHotel(h);
		return roomDetailsRepository.save(r);
	}

	@Override
	public String removeRoomDetailsById(int room_id) {
		roomDetailsRepository.deleteById(room_id);
		return "Room Details Deleted";
	}

	@Override
	public List<RoomDetails> showAllRoomDetails() {
		return roomDetailsRepository.findAll();
	}

	@Override
	public RoomDetails showRoomDetailsById(int room_id) {
		return roomDetailsRepository.findById(room_id).get();
	}
	
	public RoomDetails roomAvailabilityTrue(int roomId) {
		RoomDetails r = roomDetailsRepository.findById(roomId).get();
		r.setAvailable(true);
		return roomDetailsRepository.save(r);
	}
	
	public RoomDetails roomAvailabilityFalse(int roomId) {
		RoomDetails r = roomDetailsRepository.findById(roomId).get();
		r.setAvailable(false);
		return roomDetailsRepository.save(r);
	}

}
