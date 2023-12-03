package com.psl.ecommerceapp.ECommerceApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psl.ecommerceapp.ECommerceApp.entity.CartOrder;
import com.psl.ecommerceapp.ECommerceApp.entity.Person;

public interface OrderRepository extends JpaRepository<CartOrder, Long> {
    List<CartOrder> findByPerson(Person person);
}
