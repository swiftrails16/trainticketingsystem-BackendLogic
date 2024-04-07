package com.swiftrails.SWIFTRAILS.services;

import com.swiftrails.SWIFTRAILS.entities.User;

public interface UserService {

	public User findByEmail(String s);

	public String registerUser(User user);

	public User updateUser();

	public String validateUser(String email, String userOTP);

	public String deleteUser(String email);

	String updateUser(String email, String password, String firstName, String lastName, String address,
			String phoneNumber);

//	public User loginUser(String email,String password);

}
