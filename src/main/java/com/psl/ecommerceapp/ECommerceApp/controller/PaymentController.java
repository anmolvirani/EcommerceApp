package com.psl.ecommerceapp.ECommerceApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {

    @GetMapping("/api/payment")
    public String payment() {
        return "payment";
    }

}
