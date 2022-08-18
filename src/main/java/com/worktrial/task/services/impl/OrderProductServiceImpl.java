package com.worktrial.task.services.impl;

import com.worktrial.task.models.OrderProduct;
import com.worktrial.task.models.Product;
import com.worktrial.task.repositories.OrderProductRepository;
import com.worktrial.task.services.OrderProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class OrderProductServiceImpl implements OrderProductService {
    private final OrderProductRepository orderProductRepository;

    @Override
    public OrderProduct createOrderProduct(OrderProduct orderProduct) {
        return orderProductRepository.save(orderProduct);
    }

    @Override
    public List<OrderProduct> getOrderProductByProduct(Product product) {
        return orderProductRepository.findOrderProductByProduct(product);
    }
}
