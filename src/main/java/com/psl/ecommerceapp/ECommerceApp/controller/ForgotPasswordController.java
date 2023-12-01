package com.psl.ecommerceapp.ECommerceApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psl.ecommerceapp.ECommerceApp.entity.Person;
import com.psl.ecommerceapp.ECommerceApp.model.UserLoginRequest;
import com.psl.ecommerceapp.ECommerceApp.model.Username;
import com.psl.ecommerceapp.ECommerceApp.repository.PersonRepository;
import com.psl.ecommerceapp.ECommerceApp.service.EmailService;
import com.psl.ecommerceapp.ECommerceApp.utils.OTPGenerator;

@Controller
@RequestMapping("/api")
public class ForgotPasswordController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping("/forgotpassword")
    public String forgotPassword() {
        return "forgotpasswordusername";
    }

    @PostMapping("/forgotpasswordsubmit")
    public String forgotpasswordSubmit(@ModelAttribute Username username) {
        Person user = personRepository.findByUsername(username.getUsername());
        String otp = OTPGenerator.generateOTP();
        user.setOtp(otp);
        personRepository.save(user);

        // Send OTP via email
        emailService.sendOTPEmail(user.getEmail(), otp);
        return "forgotpasswordotp";
    }

    @PostMapping("/forgotpasswordreset")
    public String forgotPasswordReset(@ModelAttribute UserLoginRequest loginRequest) {
        Person user = personRepository.findByUsernameAndOtp(loginRequest.getUsername(),loginRequest.getOtp());
        if (user == null) {
            return "invalidotp";
        }
        user.setPassword(loginRequest.getPassword());
        personRepository.save(user);
        return "passwordresetsuccess";
    }

}
