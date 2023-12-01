package com.psl.ecommerceapp.ECommerceApp.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String otp;
    // Other fields, getters, setters, and constructors

    // @OneToMany(mappedBy = "person", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    // private List<CartItem> cart;
}
