package com.capg.hotelbookingmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.hotelbookingmanagementsystem.entity.RoomDetails;
@Repository
public interface RoomDetailsRepository extends JpaRepository<RoomDetails, Integer> {

}
