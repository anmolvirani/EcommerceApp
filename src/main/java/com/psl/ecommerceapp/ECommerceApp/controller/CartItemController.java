package com.psl.ecommerceapp.ECommerceApp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.psl.ecommerceapp.ECommerceApp.entity.CartItem;
import com.psl.ecommerceapp.ECommerceApp.entity.Person;
import com.psl.ecommerceapp.ECommerceApp.repository.CartItemRepository;
import com.psl.ecommerceapp.ECommerceApp.repository.PersonRepository;

@RestController
@RequestMapping("/api/cart")
public class CartItemController {

    @Autowired
    private PersonRepository userRepository; // Assuming you have a UserRepository

    @Autowired
    private CartItemRepository cartItemRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody CartItem cartItem, HttpSession session) {
        // Authentication authentication =
        // SecurityContextHolder.getContext().getAuthentication();
        // Get the currently authenticated user's username

        String username = session.getAttribute("username").toString();

        Person user = userRepository.findByUsername(username);
        if (cartItemRepository.findByProductNameAndPerson(cartItem.getProductName(),user) == null) {
            cartItem.setQuantity(1);
            cartItem.setPerson(user);
            cartItemRepository.save(cartItem);
        } else {
            CartItem cartItem2 = cartItemRepository.findByProductNameAndPerson(cartItem.getProductName(),user);
            cartItem2.setQuantity(cartItem2.getQuantity() + 1);
            cartItemRepository.save(cartItem2);
        }

        return new ResponseEntity<>("Item added to cart.", HttpStatus.OK);
    }

    // Other cart-related operations (e.g., remove items, view cart, checkout, etc.)
    @GetMapping("/")
    public ResponseEntity<Object> viewCart(HttpSession session) {
        String username = "";
        if (session.getAttribute("username") != null) {
            username = session.getAttribute("username").toString();

            Person user = userRepository.findByUsername(username);
            List<CartItem> cartItems = cartItemRepository.findByPerson(user);
            if (cartItems != null) {
                return new ResponseEntity<>(cartItems, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<Object>("You're not logged into your account!", HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable Long itemId) {

        if (cartItemRepository.findById(itemId) != null) {
            cartItemRepository.deleteById(itemId);
            return ResponseEntity.ok("Cart item deleted successfully");
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
