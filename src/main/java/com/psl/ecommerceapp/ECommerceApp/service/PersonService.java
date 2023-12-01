package com.psl.ecommerceapp.ECommerceApp.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.psl.ecommerceapp.ECommerceApp.entity.Person;
import com.psl.ecommerceapp.ECommerceApp.model.ChangePasswordRequest;
import com.psl.ecommerceapp.ECommerceApp.repository.PersonRepository;
import com.psl.ecommerceapp.ECommerceApp.utils.OTPGenerator;

@Service
public class PersonService {

    @Autowired
    private PersonRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Map<String, String> otpData = new HashMap<>();

    public void generateOTP(String username) {
        String otp = OTPGenerator.generateOTP();
        otpData.put(username, otp);
    }

    public boolean validateOTP(String username, String otp) {
        String storedOTP = otpData.get(username);
        return otp != null && otp.equals(storedOTP);
    }

    public boolean changePassword(String username, ChangePasswordRequest changePasswordRequest) {
        Person user = userRepository.findByUsername(username);

        if (user != null && passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            // The old password is correct
            user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
            userRepository.save(user);
            return true;
        }

        return false;
    }
}
