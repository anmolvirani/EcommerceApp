package com.psl.ecommerceapp.ECommerceApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.psl.ecommerceapp.ECommerceApp.entity.Person;
import com.psl.ecommerceapp.ECommerceApp.repository.PersonRepository;

@Controller
public class RegistrationController {

    @Autowired
    private PersonRepository userRepository;

    @PostMapping(path = "/api/register")
    public String registerUser(@ModelAttribute Person user, Model model) {
        // Person user = new Person();
        // user.setUsername(personDto.getUsername());
        // user.setPassword(personDto.getPassword());
        // user.setEmail(personDto.getEmail());
        userRepository.save(user);
        return "redirect:/api/generate-otp?username="+user.getUsername(); // Redirect to login page
    }

    @GetMapping("/api/")
    public String showRegistrationForm() {
        return "registration";
    }
}
