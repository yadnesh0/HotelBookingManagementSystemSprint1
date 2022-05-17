package com.capg.hotelbookingmanagementsystem.service;

import java.util.Optional;

import com.capg.hotelbookingmanagementsystem.entity.Admin;

public interface AdminService {
	public Admin addAdmin(Admin admin);

	public boolean validateAdmin(String username, String password);

	public Optional<Admin> viewByAdminUserName(String username, String password);

}
