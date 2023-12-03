package com.psl.ecommerceapp.ECommerceApp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psl.ecommerceapp.ECommerceApp.entity.CartItem;
import com.psl.ecommerceapp.ECommerceApp.entity.CartOrder;
import com.psl.ecommerceapp.ECommerceApp.entity.Person;
import com.psl.ecommerceapp.ECommerceApp.repository.CartItemRepository;
import com.psl.ecommerceapp.ECommerceApp.repository.OrderRepository;
import com.psl.ecommerceapp.ECommerceApp.repository.PersonRepository;

@Controller
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/confirmOrder")
    public String createOrder(HttpSession session) {
        String username = session.getAttribute("username").toString();
        CartOrder order = new CartOrder();
        Person user = personRepository.findByUsername(username);
        List<CartItem> cartItems = cartItemRepository.findByPerson(user);
        order.setCartItem(cartItems);
        order.setPerson(user);
        orderRepository.save(order);
        return "orderSuccessful";
    }

    @GetMapping("/order/")
    public ResponseEntity<List<CartOrder>> listOfOrder(HttpSession session) {
        String username = session.getAttribute("username").toString();
        Person user = personRepository.findByUsername(username);
        List<CartOrder> cartOrders = orderRepository.findByPerson(user);
        return new ResponseEntity<>(cartOrders, HttpStatus.OK);
    }

    @GetMapping("/orderList")
    public String orderList() {
        return "orderList";
    }

}
