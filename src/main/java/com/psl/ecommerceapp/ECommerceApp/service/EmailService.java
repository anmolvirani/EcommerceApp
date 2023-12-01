package com.psl.ecommerceapp.ECommerceApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment environment;

    public void sendOTPEmail(String recipientEmail, String otp) {
        String fromEmail = environment.getProperty("spring.mail.username");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromEmail);
        mailMessage.setTo(recipientEmail);
        mailMessage.setSubject("Your OTP for Login");
        mailMessage.setText("Your OTP is: " + otp);

        // javaMailSender.send(mailMessage);
    }

}
