package com.swiftrails.SWIFTRAILS.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swiftrails.SWIFTRAILS.entities.User;
import com.swiftrails.SWIFTRAILS.impl.TrainsSceduleAPIServiceImpl;
import com.swiftrails.SWIFTRAILS.impl.UserServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
	
	@Autowired
	public UserServiceImpl userservice;
	
	@Autowired
	public TrainsSceduleAPIServiceImpl trainssceduleapiserviceimpl;
	
	@PostMapping("/registerUser")
	public ResponseEntity<String>  registerUser(@RequestBody User user) {
		String res=userservice.registerUser(user);
		return ResponseEntity.ok(res);
	}
	
	
	@PostMapping("/findEmail")
    public User findEmail(@RequestParam String email) {
        User u=userservice.findByEmail(email);
        return u;
    }
	
	@PutMapping("/validateOTP")
	public ResponseEntity<String> validateOTP(@RequestBody User user){
		String email= user.getEmail();
		String otp=user.getOtp();
		try {
			String result=userservice.validateUser(email,otp);
			return ResponseEntity.ok(result);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
		}
	}
	@DeleteMapping("/deleteUser")
	public ResponseEntity<String> deleteUser(@RequestBody Map<String, String> request){
	    String email = request.get("email");
	    String res = userservice.deleteUser(email);
	    return ResponseEntity.ok(res);
	}
	
	@GetMapping("/stationCodes")
	public String fetchTrainStationsCodes(@RequestParam String fromStationCode,
								            @RequestParam String toStationCode,
								            @RequestParam String dateOfJourney) {
		String stationCodes = trainssceduleapiserviceimpl.fetchTrainStationsCodes(fromStationCode, toStationCode, dateOfJourney); 
		
		return stationCodes;
	}
	
//	@PostMapping("/login")
//	public ResponseEntity<User> login(@RequestBody Map<String, String> request) {
//	    String email = request.get("email");
//	    String password = request.get("password");
//	    User u = userservice.loginUser(email, password);
//	    if (u != null) {
//	        return ResponseEntity.ok(u);
//	    } else {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//	    }
//	}


	
	
}
