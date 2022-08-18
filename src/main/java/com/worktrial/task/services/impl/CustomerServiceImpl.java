package com.worktrial.task.services.impl;

import com.worktrial.task.dtos.CustomerDto;
import com.worktrial.task.enums.CustomerCategory;
import com.worktrial.task.exceptions.BaseException;
import com.worktrial.task.exceptions.CustomerException;
import com.worktrial.task.models.Customer;
import com.worktrial.task.models.CustomerAddress;
import com.worktrial.task.models.OrderProduct;
import com.worktrial.task.models.Product;
import com.worktrial.task.repositories.CustomerRepository;
import com.worktrial.task.repositories.specification.CustomerSpecification;
import com.worktrial.task.services.CustomerAddressService;
import com.worktrial.task.services.CustomerService;
import com.worktrial.task.services.OrderProductService;
import com.worktrial.task.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerAddressService customerAddressService;
    private final CustomerSpecification customerSpecification;
    private final OrderProductService orderProductService;
    private final ProductService productService;

    @Override
    public List<Customer> searchCustomer(String lastName, String firstName, String emailAddress, String category,
                                         String city, String country, String state) throws BaseException {
        Specification<Customer> spec = customerSpecification.allCustomer();
        if (StringUtils.hasText(lastName)) {
            spec = spec.and(customerSpecification.customerByLastName(lastName));
        }
        if (StringUtils.hasText(firstName)) {
            spec = spec.and(customerSpecification.customerByFirstName(firstName));
        }
        if (StringUtils.hasText(emailAddress)) {
            spec = spec.and(customerSpecification.customerByEmailAddress(emailAddress));
        }
        if (StringUtils.hasText(category)) {
            CustomerCategory customerCategory = CustomerCategory.fromString(category);
            spec = spec.and(customerSpecification.customerByCategory(customerCategory));
        }
        if (StringUtils.hasText(country)) {
            spec = spec.and(customerSpecification.customerByCountry(country));
        }
        if (StringUtils.hasText(state)) {
            spec = spec.and(customerSpecification.customerByState(state));
        }
        if (StringUtils.hasText(city)) {
            spec = spec.and(customerSpecification.customerByCity(city));
        }
        List<Customer> customers = customerRepository.findAll(spec);
        if (CollectionUtils.isEmpty(customers)) {
            throw new CustomerException(CustomerException.CustomerError.NOT_FOUND);
        }
        return customers;
    }

    public Customer createCustomer(CustomerDto customerDto) {
        CustomerAddress customerAddress = customerAddressService.createAddress(customerDto.getCustomerAddress());
        Customer customer = new Customer();
        customer.setLastName(customerDto.getLastName());
        customer.setFirstName(customerDto.getFirstName());
        customer.setBirthdate(customerDto.getBirthdate());
        customer.setCustomerAddress(customerAddress);
        customer.setJobTitle(customerDto.getJobTitle());
        customer.setCategory(customerDto.getCategory());
        customer.setEmailAddress(customerDto.getEmailAddress());
        return customerRepository.save(customer);
    }

    public Customer findCustomerById(String customerId) throws BaseException {
        return customerRepository.findById(customerId).orElseThrow(() -> new CustomerException(CustomerException.CustomerError.NOT_FOUND));
    }

    public List<Customer> findCustomerByProductId(String productId) throws BaseException {
        Product product = productService.findProductById(productId);
        List<OrderProduct> orderProducts = orderProductService.getOrderProductByProduct(product);
        List<Customer> customers = orderProducts.stream().map(orderProduct -> orderProduct.getOrder().getCustomer()).distinct().collect(Collectors.toList());
        if (CollectionUtils.isEmpty(customers)) {
            throw new CustomerException(CustomerException.CustomerError.EMPTY_CUSTOMER_LIST);
        }
        return customers;
    }

    public Customer deleteCustomerByCustomerId(String customerId) throws BaseException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerException(CustomerException.CustomerError.NOT_FOUND));
        customerRepository.delete(customer);
        return customer;
    }
}
