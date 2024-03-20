package com.swiftrails.SWIFTRAILS.services;


import org.springframework.stereotype.Service;


import com.swiftrails.SWIFTRAILS.entities.User;

public interface UserService {
	
	public User findByEmail(String s);
	
	public String registerUser(User user);
	
	public User updateUser();
	
	public String validateUser(String email,String userOTP);
	
	public String deleteUser(String email);
	
//	public User loginUser(String email,String password);

}
