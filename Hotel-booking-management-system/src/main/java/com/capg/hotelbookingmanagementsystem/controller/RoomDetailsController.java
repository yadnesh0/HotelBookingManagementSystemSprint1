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

import com.capg.hotelbookingmanagementsystem.entity.RoomDetails;
import com.capg.hotelbookingmanagementsystem.exception.RecordNotFoundException;
import com.capg.hotelbookingmanagementsystem.repository.RoomDetailsRepository;
import com.capg.hotelbookingmanagementsystem.service.RoomDetailsServiceImpl;

@RestController
@RequestMapping("/roomdetails")
public class RoomDetailsController {
     @Autowired
     private RoomDetailsRepository roomDetailsRepository;
    @Autowired
	private RoomDetailsServiceImpl roomDetailsServiceImpl;
	
	 @PostMapping("/createRoomDetails")
	 public ResponseEntity<RoomDetails> createRoomDetails(@RequestBody RoomDetails roomDetails)
	 {
		 RoomDetails rd=roomDetailsServiceImpl.addRoomDetails(roomDetails);
		 return new ResponseEntity<RoomDetails>(rd,HttpStatus.CREATED);
	 }
	 
	 @PutMapping("/updateRoomDetails")
	 public ResponseEntity<RoomDetails> updateRoomDetails(@RequestBody RoomDetails roomDetails)throws RecordNotFoundException{
		 Integer id=roomDetails.getRoomId();
		 if(roomDetailsRepository.findById(id).isPresent()){

			 return new ResponseEntity<RoomDetails>(roomDetailsServiceImpl.updateRoomDetails(roomDetails),HttpStatus.CREATED);
		 }
		 else {
			 throw new RecordNotFoundException("Rooms Details with Id : "+id+" not found ");
		 }
	 }
	 @DeleteMapping("/deletedById/{room_id}")
	 public ResponseEntity<String> deleteRooomDetailsById(@PathVariable int room_id) throws RecordNotFoundException{

		 if(roomDetailsRepository.findById(room_id).isPresent()) {
			 String rd=roomDetailsServiceImpl.removeRoomDetailsById(room_id);
			 return new ResponseEntity<String>(rd,HttpStatus.OK);
		 }
		 else {
			 throw new RecordNotFoundException("User with Id : "+room_id+" not found ");
		 }
	 }

	 @GetMapping("/listRoomDetails")
	 public List<RoomDetails> getAllr()
	 {
		 return roomDetailsServiceImpl.showAllRoomDetails();
	 }


	 @GetMapping("/getRoomDetailsById/{room_id}")
	 public ResponseEntity<RoomDetails> getRoomDetailsById(@PathVariable int room_id) throws RecordNotFoundException{

		 if(roomDetailsRepository.findById(room_id).isPresent()) {
			 RoomDetails rd=roomDetailsServiceImpl.showRoomDetailsById(room_id);
			 return new ResponseEntity<RoomDetails>(rd,HttpStatus.OK);
		 }
		 else {
			 throw new RecordNotFoundException("RoomDetails with Id : "+room_id+" not found ");
		 }
	 }
	 
	 @PutMapping("/setTrue/{room_id}")
	 public ResponseEntity<RoomDetails> setRoomTrue(@PathVariable int room_id) throws RecordNotFoundException{

		 if(roomDetailsRepository.findById(room_id).isPresent()) {
			 RoomDetails rd=roomDetailsServiceImpl.roomAvailabilityTrue(room_id);
			 return new ResponseEntity<RoomDetails>(rd,HttpStatus.OK);
		 }
		 else {
			 throw new RecordNotFoundException("RoomDetails with Id : "+room_id+" not found ");
		 }
	 }
	 
	 @PutMapping("/setFalse/{room_id}")
	 public ResponseEntity<RoomDetails> setRoomFalse(@PathVariable int room_id) throws RecordNotFoundException{

		 if(roomDetailsRepository.findById(room_id).isPresent()) {
			 RoomDetails rd=roomDetailsServiceImpl.roomAvailabilityFalse(room_id);
			 return new ResponseEntity<RoomDetails>(rd,HttpStatus.OK);
		 }
		 else {
			 throw new RecordNotFoundException("RoomDetails with Id : "+room_id+" not found ");
		 }
	 }

}
	 

