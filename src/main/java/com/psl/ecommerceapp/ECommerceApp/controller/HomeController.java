package com.psl.ecommerceapp.ECommerceApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/api/home")
    public String displayHomePage() {
        return "home";
    }
}
