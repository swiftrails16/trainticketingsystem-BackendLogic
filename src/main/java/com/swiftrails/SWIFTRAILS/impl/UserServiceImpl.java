//package com.swiftrails.SWIFTRAILS.impl;
//
//import com.swiftrails.SWIFTRAILS.entities.User;
//import com.swiftrails.SWIFTRAILS.repositories.UserRepository;
//import com.swiftrails.SWIFTRAILS.services.UserService;
//
//public class UserServiceImpl implements UserService {
//	
//    @Override
//    public User registerUser(User user) {
//    	String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//
//       // Save the user details
//       User savedUser = UserRepository.save(user);
//
//       UserRepository.save(savedUser);
//       System.out.println("savedUsers: " + savedUser);
//
//       return savedUser;
//    }
//
//}
