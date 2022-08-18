package com.worktrial.task.controllers;


import com.worktrial.task.dtos.ProductDto;
import com.worktrial.task.exceptions.BaseException;
import com.worktrial.task.models.Customer;
import com.worktrial.task.models.Product;
import com.worktrial.task.services.CustomerService;
import com.worktrial.task.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.createProduct(productDto));
    }

    @GetMapping (path = "{productId}/customers")
    public ResponseEntity<List<Customer>> getCustomerFromProduct(@PathVariable String productId) throws BaseException {
        return ResponseEntity.ok(customerService.findCustomerByProductId(productId));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() throws BaseException {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
