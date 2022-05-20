package com.capg.hotelbookingmanagementsystem.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(updatable = false)
	private int userId;
	
	@Column(name = "user_name",unique = true, updatable = false)
	@NotBlank(message = "Username is mandatory")
	@Size(min = 3, message = "Username must contain 3 characters.")
	private String userName;
	
	@Column(name = "email_address",unique = true,nullable = false)
	private String email;
	
	@NotBlank(message = "Password is mandatory")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$", message="Password must contain a lowercase character, "
                                        + "a uppercase character and a digit, minimum length must be 6 characters")
	private String password;
	
	@NotBlank(message = "Mobile is mandatory")
	private String mobile;
	
	@NotBlank(message = "Address is mandatory")
	private String address;
	
	@OneToOne(mappedBy = "users",cascade = CascadeType.ALL)
	private BookingDetails booking;
	
	public User(int userId) {
		this.userId = userId;
	}
	
	
}
