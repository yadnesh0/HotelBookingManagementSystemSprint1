package com.capg.hotelbookingmanagementsystem.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.hotelbookingmanagementsystem.entity.User;
import com.capg.hotelbookingmanagementsystem.exception.UserNotFoundException;
import com.capg.hotelbookingmanagementsystem.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public User addUser(User user) {
		if(user == null)
			throw new UserNotFoundException("Invalid Customer Data");
		return userRepository.save(user);
	}

	@Override 
	public User updateUser(User user) { 
		return userRepository.save(user);
	}

	@Override
	public String removeUserById(int user_id) { 
		userRepository.deleteById(user_id); return "User deleted"; 
	}

	@Override 
	public List<User> showAllUsers(){
		if(userRepository.findAll() == null)
			 new UserNotFoundException("No Customer found");
		return userRepository.findAll();
	}

	@Override
	public User showUserById(int user_id) { 
		return userRepository.findById(user_id).get(); 
	}


	@Override
	public boolean validateUser(String userName, String password) {
		Optional<User> user = userRepository.findByUserNameAndPassword(userName, password);
		if(user.get() == null)
			return false;
		else
			return true;
	}
	public Optional<User> viewByUserName(String userName, String password) {
		Optional<User> user = userRepository.findByUserNameAndPassword(userName, password);
		if(user.get() == null)
			throw new UserNotFoundException("Customer not created");
		return user;		
	}
}


