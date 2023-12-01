package com.psl.ecommerceapp.ECommerceApp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {
    private String username;
    private String password;
    private String otp;
}
