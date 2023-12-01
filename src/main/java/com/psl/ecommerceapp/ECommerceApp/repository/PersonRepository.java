package com.psl.ecommerceapp.ECommerceApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psl.ecommerceapp.ECommerceApp.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByUsername(String username);

    Person findByUsernameAndPassword(String username,String password);

    Person findByUsernameAndPasswordAndOtp(String username,String password,String otp);

    Person findByUsernameAndOtp(String username,String otp);
}
