package com.worktrial.task.controllers;

import com.worktrial.task.dtos.CustomerDto;
import com.worktrial.task.dtos.OrderResponseDto;
import com.worktrial.task.enums.CustomerCategory;
import com.worktrial.task.exceptions.BaseException;
import com.worktrial.task.models.Customer;
import com.worktrial.task.services.CustomerService;
import com.worktrial.task.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Customer>> searchCustomer(@RequestParam (required = false) String lastName,
                                                         @RequestParam (required = false) String firstName,
                                                         @RequestParam (required = false) String emailAddress,
                                                         @RequestParam (required = false) String category,
                                                         @RequestParam (required = false) String city,
                                                         @RequestParam (required = false) String country,
                                                         @RequestParam (required = false) String state) throws BaseException {
        List<Customer> customers = customerService.searchCustomer(lastName, firstName, emailAddress, category, city, country, state);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody CustomerDto customer) {
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @GetMapping(path = "{customerId}/orders")
    public ResponseEntity<List<OrderResponseDto>> getOrderFromCustomer(@PathVariable String customerId) throws BaseException {
        return ResponseEntity.ok(orderService.findOrderByCustomerId(customerId));
    }

    @DeleteMapping(path = "/{customerId}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable String customerId) throws BaseException {
        customerService.deleteCustomerByCustomerId(customerId);
        return ResponseEntity.noContent().build();
    }



}