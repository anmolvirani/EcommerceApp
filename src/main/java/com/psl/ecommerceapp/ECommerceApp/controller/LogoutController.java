package com.psl.ecommerceapp.ECommerceApp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LogoutController {

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.removeAttribute("username");
        return new ResponseEntity<>("Logged out successfully!", HttpStatus.OK);
    }

}
