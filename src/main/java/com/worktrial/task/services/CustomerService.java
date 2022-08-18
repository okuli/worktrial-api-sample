package com.worktrial.task.services;

import com.worktrial.task.dtos.CustomerDto;
import com.worktrial.task.exceptions.BaseException;
import com.worktrial.task.models.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> searchCustomer(String name, String firstName, String emailAddress, String category,
                                  String city, String country, String state) throws BaseException;
    Customer createCustomer(CustomerDto customerDto);
    Customer findCustomerById(String customerId) throws BaseException;
    List<Customer> findCustomerByProductId(String productId) throws BaseException;
    Customer deleteCustomerByCustomerId(String customerId) throws BaseException;
}
