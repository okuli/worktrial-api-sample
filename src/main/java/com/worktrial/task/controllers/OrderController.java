package com.worktrial.task.controllers;


import com.worktrial.task.dtos.OrderRequestDto;
import com.worktrial.task.dtos.OrderResponseDto;
import com.worktrial.task.exceptions.BaseException;
import com.worktrial.task.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) throws BaseException {
        return ResponseEntity.ok(orderService.createOrder(orderRequestDto));
    }

    @GetMapping(path = "order/new-customer")
    public ResponseEntity<List<OrderResponseDto>> getOrderByNewCustomer() throws BaseException{
        return ResponseEntity.ok(orderService.findOrderByNewCustomer());
    }
}
