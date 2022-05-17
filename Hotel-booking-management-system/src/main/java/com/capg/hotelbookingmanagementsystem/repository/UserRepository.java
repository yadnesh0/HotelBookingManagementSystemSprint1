package com.capg.hotelbookingmanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.hotelbookingmanagementsystem.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUserNameAndPassword(String username, String password);

}
