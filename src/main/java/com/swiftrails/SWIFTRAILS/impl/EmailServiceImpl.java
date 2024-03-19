//package com.swiftrails.SWIFTRAILS.impl;
//
//import java.util.ResourceBundle;
//import java.util.concurrent.CompletableFuture;
//
//import javax.mail.Session;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import com.swiftrails.SWIFTRAILS.services.EmailService;
//
//import jakarta.mail.internet.MimeMessage;
//
//@Service
//public class EmailServiceImpl implements EmailService {
//
//	   private final JavaMailSender mailSender;
//
//	    @Autowired
//	    public EmailServiceImpl(JavaMailSender mailSender) {
//	        this.mailSender = mailSender;
//	    }
//	    
//	    ResourceBundle rb = ResourceBundle.getBundle("application");
//	    
////	@Override
////	public CompletableFuture<Void> sendEmail(String to, String subject, String text) {
////		CompletableFuture<Void> future = new CompletableFuture<>();
////
////        try {
////            MimeMessage message = mailSender.createMimeMessage();
////            MimeMessageHelper helper = new MimeMessageHelper(message, true);
////            helper.setTo(to);
////            // No need to set the "from" address; it is automatically set by Spring Boot based on your properties
////            helper.setSubject(subject);
////            helper.setText(text, true); // Set the second parameter to true to send HTML content
////            mailSender.send(message);
////
////            future.complete(null); // Indicate that the email sending is successful
////        } catch (jakarta.mail.MessagingException e) {
////            e.printStackTrace();
////            future.completeExceptionally(e); // Indicate that the email sending failed
////        } catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////
////        return future;
////    }
////
////	@Override
////	public String getOtpLoginEmailTemplate(String name, String otp) {
////		// Create the formatted email template with the provided values
////        String emailTemplate = "<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">"
////                + "<div style=\"margin:50px auto;width:70%;padding:20px 0\">"
////                + "<div style=\"border-bottom:1px solid #eee\">"
////                + "</div>"
////                + "<p style=\"font-size:1.1em\">Hi, " + name + "</p>"
////                + "<p>Thank you for choosing OneStopBank. Use the following OTP to complete your Log In procedures. OTP is valid for 5 minutes</p>"
////                + "<h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">" + otp + "</h2>"
////                + "<p style=\"font-size:0.9em;\">Regards,<br />Swiftrails</p>"
////                + "<hr style=\"border:none;border-top:1px solid #eee\" />"
////                + "</div>"
////                + "</div>";
////
////        return emailTemplate;
////	}
//	
//	public CompletableFuture<Void> sendEmail(String toEmail, String subject, String body) throws Exception {
//	    // Sender's email address and password
//	    String fromEmail = "maddinenidheeraj14@gmail.com";
//	    String password = "Rojarani@10";
//
//	    // Setup mail server properties
//	    Properties properties = new Properties();
//	    properties.put("mail.smtp.host", "smtp.gmail.com");
//	    properties.put("mail.smtp.port", "587");
//	    properties.put("mail.smtp.auth", "true");
//	    properties.put("mail.smtp.starttls.enable", "true");
//	    properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//
//	    // Get the Session object
//	    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
//	        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//	            return new javax.mail.PasswordAuthentication(fromEmail, password);
//	        }
//	    });
//
//	    try {
//	        // Create a default MimeMessage object
//	        MimeMessage message = new MimeMessage(session);
//
//	        // Set the sender and recipient addresses
//	        message.setFrom(new InternetAddress(fromEmail));
//	        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
//
//	        // Set the email subject and body
//	        message.setSubject(subject);
//	        message.setText(body);
//
//	        // Send the email
//	        Transport.send(message);
//
//	        System.out.println("Email sent successfully!");
//
//	    } catch (Exception e) {
//	        throw new Exception("Error sending email: " + e.getMessage());
//	    }
//	}
//
//	// New method to generate a random OTP
//	private String generateOTP() {
//	    int otp = (int) ((Math.random() * 900000) + 100000);
//	    return String.valueOf(otp);
//	}
//	private void sendOTPEmail(String email, String otp) {
//	    // Implement the logic to send OTP to the user's email
//	    // Use the email-sending code from the previous response
//	    // Make sure to secure your email sending mechanism
//	    // Example:
//	    try {
//	        String subject = "Your OTP for Registration";
//	        String body = "Your OTP for registration is: " + otp;
//
//	        sendEmail(email, subject, body);
//	    } catch (Exception e) {
//	        System.out.println("Error sending OTP email: " + e.getMessage());
//	    }
//	}
//
//
//}
//
