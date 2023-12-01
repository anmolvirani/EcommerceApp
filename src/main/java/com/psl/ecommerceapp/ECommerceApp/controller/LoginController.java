package com.psl.ecommerceapp.ECommerceApp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.psl.ecommerceapp.ECommerceApp.entity.Person;
import com.psl.ecommerceapp.ECommerceApp.model.UserLoginRequest;
import com.psl.ecommerceapp.ECommerceApp.repository.PersonRepository;
import com.psl.ecommerceapp.ECommerceApp.service.EmailService;
import com.psl.ecommerceapp.ECommerceApp.utils.OTPGenerator;

@Controller
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/login")
    public String login(@ModelAttribute UserLoginRequest loginRequest, HttpSession session) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        String otp = loginRequest.getOtp();

        Person user = personRepository.findByUsernameAndPasswordAndOtp(username, password, otp);

        if (user != null) {
            // Successful login
            // Generate a token or manage user sessions here
            session.setAttribute("username", username);
            return "redirect:/api/productCatalog";
        } else {
            // Failed login
            //
            return "failedlogin";
        }
    }

    @GetMapping("/generate-otp")
    public String generateOTP(@RequestParam(required = false) String username) {

        Person user = personRepository.findByUsername(username);

        if (user != null) {
            String otp = OTPGenerator.generateOTP();
            user.setOtp(otp);
            personRepository.save(user);

            // Send OTP via email
            emailService.sendOTPEmail(user.getEmail(), otp);
        }

        return "login";

    }

}
