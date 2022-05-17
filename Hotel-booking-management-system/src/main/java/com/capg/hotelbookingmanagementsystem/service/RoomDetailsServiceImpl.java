package com.capg.hotelbookingmanagementsystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capg.hotelbookingmanagementsystem.entity.RoomDetails;
import com.capg.hotelbookingmanagementsystem.repository.RoomDetailsRepository;

@Service
public class RoomDetailsServiceImpl implements RoomDetailsService {
	@Autowired
    private RoomDetailsRepository roomDetailsRepository;
	
	@Override
	public RoomDetails addRoomDetails(RoomDetails roomDetails) {
		return roomDetailsRepository.save(roomDetails);
	}

	@Override
	public RoomDetails updateRoomDetails(RoomDetails roomDetails) {	
		return roomDetailsRepository.saveAndFlush(roomDetails);
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

}
