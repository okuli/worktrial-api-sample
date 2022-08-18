package com.worktrial.task.services.impl;

import com.worktrial.task.dtos.OrderRequestDto;
import com.worktrial.task.dtos.OrderResponseDto;
import com.worktrial.task.enums.CustomerCategory;
import com.worktrial.task.exceptions.BaseException;
import com.worktrial.task.exceptions.OrderException;
import com.worktrial.task.models.Customer;
import com.worktrial.task.models.Order;
import com.worktrial.task.models.OrderProduct;
import com.worktrial.task.models.Product;
import com.worktrial.task.repositories.OrderRepository;
import com.worktrial.task.services.CustomerService;
import com.worktrial.task.services.OrderProductService;
import com.worktrial.task.services.OrderService;
import com.worktrial.task.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final OrderProductService orderProductService;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) throws BaseException {
        Customer customer = customerService.findCustomerById(orderRequestDto.getCustomerId());
        List<Product> products = productService.findProductByIds(orderRequestDto.getProductIds());
        Order order = new Order();
        order.setAdditionalInfo(orderRequestDto.getAdditionalInfo());
        order.setCustomer(customer);
        order.setProducts(new ArrayList<>());
        orderRepository.save(order);
        for (Product product : products) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(product);
            orderProductService.createOrderProduct(orderProduct);
            order.getProducts().add(orderProduct);
        }
        return mapOrderToOrderResponse(order);
    }

    @Override
    public OrderResponseDto mapOrderToOrderResponse(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setCustomer(order.getCustomer());
        orderResponseDto.setAdditionalInfo(order.getAdditionalInfo());
        orderResponseDto.setProducts(order.getProducts().stream().map(OrderProduct::getProduct).collect(Collectors.toList()));
        return orderResponseDto;
    }

    @Override
    public List<OrderResponseDto> findOrderByCustomerId(String customerId) throws BaseException {
        Customer customer = customerService.findCustomerById(customerId);
        List<Order> orders = orderRepository.findOrderByCustomer(customer);
        if (CollectionUtils.isEmpty(orders)) {
            throw new OrderException(OrderException.OrderError.EMPTY_ORDER_LIST);
        }
        return orders.stream().map(this::mapOrderToOrderResponse).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDto> findOrderByNewCustomer() throws BaseException {
        List<Order> orders = orderRepository.findOrderByCustomerCategory(CustomerCategory.NEW_CUSTOMER);
        if (CollectionUtils.isEmpty(orders)) {
            throw new OrderException(OrderException.OrderError.EMPTY_ORDER_LIST);
        }
        return orders.stream().map(this::mapOrderToOrderResponse).collect(Collectors.toList());
    }
}
