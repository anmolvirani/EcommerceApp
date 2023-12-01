package com.psl.ecommerceapp.ECommerceApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.psl.ecommerceapp.ECommerceApp.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
