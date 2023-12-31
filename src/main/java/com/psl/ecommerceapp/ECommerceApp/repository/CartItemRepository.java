package com.psl.ecommerceapp.ECommerceApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psl.ecommerceapp.ECommerceApp.entity.CartItem;
import com.psl.ecommerceapp.ECommerceApp.entity.Person;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByPerson(Person person);

    CartItem findByProductName(String productName);

    CartItem findByProductNameAndPerson(String productName, Person person);

    CartItem deleteByProductName(String productName);

    List<CartItem> deleteByPerson(Person person);
}
