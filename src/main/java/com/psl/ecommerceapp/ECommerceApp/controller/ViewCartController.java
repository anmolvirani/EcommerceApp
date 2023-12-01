package com.psl.ecommerceapp.ECommerceApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class ViewCartController {

    @GetMapping("/viewCart")
    public String viewCart() {
        return "cart";
    }
}
