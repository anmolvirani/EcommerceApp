package com.psl.ecommerceapp.ECommerceApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductCatalogController {

    @GetMapping("/api/productCatalog")
    public String viewProductCatalog() {
        return "productcatalog";
    }
}
