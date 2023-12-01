package com.psl.ecommerceapp.ECommerceApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psl.ecommerceapp.ECommerceApp.entity.CartItem;
import com.psl.ecommerceapp.ECommerceApp.entity.Person;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByPerson(Person person);

    CartItem findByProductName(String productName);

    CartItem deleteByProductName(String productName);
}
