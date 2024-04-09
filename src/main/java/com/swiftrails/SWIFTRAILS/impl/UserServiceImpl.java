package com.swiftrails.SWIFTRAILS.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


import com.swiftrails.SWIFTRAILS.DBConnection.DBconnection;
import com.swiftrails.SWIFTRAILS.entities.User;
import com.swiftrails.SWIFTRAILS.services.EmailSenderService;
import com.swiftrails.SWIFTRAILS.services.UserService;
import javax.mail.MessagingException;
import org.springframework.context.event.EventListener;

@Service
public class UserServiceImpl implements UserService {

//	@Autowired
//    private UserRepository userRepo;
	@Autowired
	public DBconnection dbconnection;

	@Override
	public User findByEmail(String s) {
		User u = new User();
		String query = "SELECT * FROM customer WHERE MAILID=?";
		try {
			Connection con = dbconnection.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setAddress(rs.getString("addr"));
				u.setPassword(rs.getString("pword"));
				u.setEmail(rs.getString("mailid"));
				u.setPhone_number(rs.getString("phno"));
			} else {
				throw new Exception("User with email " + s + " not found");
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

	@Autowired
	private EmailSenderService senderService;

	@Override
	public String registerUser(String email,String password,String firstName,String lastName,String address,String phoneNumber) {
		String responseCode = null;
		String query = "INSERT INTO customer VALUES(?,?,?,?,?,?,?,?)";

		try {
			Connection con = dbconnection.getConnection();
			PreparedStatement ps = con.prepareStatement(query);

			String otp = getOTP();
			boolean is_active = false;

			System.out.println("This is the mail ID: " + email);
//			ps.setString(1, user.getEmail());
//			ps.setString(2, user.getPassword());
//			ps.setString(3, user.getFname());
//			ps.setString(4, user.getLname());
//			ps.setString(5, user.getAddress());
//			ps.setString(6, user.getPhone_number());
//			ps.setString(7, otp);
//			ps.setBoolean(8, is_active);
//
//			String mailId = user.getEmail();
			ps.setString(1, email);
			ps.setString(2, password);
			ps.setString(3, firstName);
			ps.setString(4, lastName);
			ps.setString(5, address);
			ps.setString(6,phoneNumber);
			ps.setString(7, otp);
			ps.setBoolean(8, is_active);

			String mailId = email;
			
			System.out.println(mailId);
			int rs = ps.executeUpdate();
			if (rs > 0) {
				triggerMail(mailId, otp);
				responseCode = "Sent an OTP to the given Mail ID";
			}

			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().toUpperCase().contains("ORA-00001")) {
				responseCode += " : " + "User With Id: " + email + " is already registered ";
			} else {
				responseCode += " : " + e.getMessage();
				System.out.println(responseCode);
			}
		}

		return responseCode;
	}

	public void triggerMail(String email, String otp) throws MessagingException {
		String emailBody = "THIS is Your OTP: " + otp + "\nWelcome to SWIFTRAILS";
		senderService.sendEmail(email, "Welcome to SWIFTRAILS", emailBody);
	}

	public String getOTP() {
		int otp = (int) ((Math.random() * 900000) + 100000);
		return String.valueOf(otp);
	}

	@Override
	public User updateUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String validateUser(String email, String userOTP) {
//		User u = new User();
		String s = "";
		String query = "SELECT otp FROM customer WHERE MAILID=?";
		try {
			Connection con = dbconnection.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String actualOTP = rs.getString("otp");
				System.out.println("OTP value: " + actualOTP);

				if (actualOTP.equals(userOTP)) {
					s = updateisActive(email);
				}
			} else {
				throw new Exception("Please try again!!!");
			}
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public String updateisActive(String email) {
		String result = "";
		String query2 = "UPDATE customer SET is_Active = true WHERE MAILID =?";
		try {
			Connection con = dbconnection.getConnection();
			PreparedStatement ps2 = con.prepareStatement(query2);
			ps2.setString(1, email);
			int rs2 = ps2.executeUpdate();
			if (rs2 > 0) {
				result = "Validated Successfully";
			} else {
				result = "Please enter correct OTP";
			}
			ps2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public String updateUser(String email, String password, String firstName, String lastName, String address, String phoneNumber) {
	    String result;
	    String query = "UPDATE CUSTOMER SET PWORD = ?, FNAME = ?, LNAME = ?, ADDR = ?, PHNO = ? WHERE MAILID = ?";
	    try {
	        Connection con = dbconnection.getConnection();
	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setString(1, password);
	        ps.setString(2, firstName);
	        ps.setString(3, lastName);
	        ps.setString(4, address);
	        ps.setString(5, phoneNumber);
	        ps.setString(6, email);
	        int rowsAffected = ps.executeUpdate();
	        System.out.println("Updated rows affected: " + rowsAffected);
	        if (rowsAffected > 0) {
	            result = "User Updated Successfully";
	        } else {
	            result = "No user found with the provided email, or user could not be updated.";
	        }
	    } catch (SQLException e) {
	        // Log the exception details here instead of printStackTrace
	        e.printStackTrace();
	        result = "SQLException occurred: " + e.getMessage();
	    }
	    return result;
	}


	@Override
	public String deleteUser(String email) {
		String result;
		String query = "DELETE FROM customer WHERE MAILID=?";
		try {
			Connection con = dbconnection.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
			int rowsAffected = ps.executeUpdate();
			System.out.println("Delete rows effected:" + rowsAffected);
			if (rowsAffected > 0) {
				result = "User Deleted Successfully";
			} else {
				result = "No user found with the provided email, or user could not be deleted.";
			}
		}catch (NullPointerException e) {
			e.printStackTrace();
			result = "NullPointerException occurred: " + e.getMessage();
		} catch (SQLException e) {
			// Log the exception details here instead of printStackTrace
			e.printStackTrace();
			result = "SQLException occurred: " + e.getMessage();
		}
		return result;
	}

	@Override
	public User loginUser(String email, String password) {
		User u = new User();
		String result;
		String query = "select mailid,pword from customer where mailid=?";
		try {
			Connection con = dbconnection.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				u.setEmail(rs.getString("MAILID"));
				u.setPassword(rs.getString("PWORD"));
			} else {
				throw new Exception("UserName/Email is not correct");
			}
			ps.close();
			String actualMail=u.getEmail();
			String actualPword=u.getPassword();
			if(actualMail.equals(email) && actualPword.equals(password)) {
				u=findByEmail(email);
				u.setFname(rs.getString("fname"));
				u.setLname(rs.getString("lname"));
				u.setAddress(rs.getString("addr"));
				u.setPassword(rs.getString("pword"));
				u.setEmail(rs.getString("mailid"));
				u.setPhone_number(rs.getString("phno"));
			}
		} catch (Exception e) {
			// Log the exception details here instead of printStackTrace
			e.printStackTrace();
			result = "SQLException occurred: " + e.getMessage();
			System.out.println(result);
		}
		return u;
	}

}
