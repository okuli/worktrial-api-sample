package com.worktrial.task.services;

import com.worktrial.task.dtos.OrderRequestDto;
import com.worktrial.task.dtos.OrderResponseDto;
import com.worktrial.task.exceptions.BaseException;
import com.worktrial.task.models.Order;

import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto) throws BaseException;
    OrderResponseDto mapOrderToOrderResponse(Order order);
    List<OrderResponseDto> findOrderByCustomerId(String customerId) throws BaseException;
    List<OrderResponseDto> findOrderByNewCustomer() throws BaseException;
}
