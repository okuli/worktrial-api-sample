package com.worktrial.task.services;

import com.worktrial.task.models.OrderProduct;
import com.worktrial.task.models.Product;

import java.util.List;

public interface OrderProductService {
    OrderProduct createOrderProduct(OrderProduct orderProduct);
    List<OrderProduct> getOrderProductByProduct(Product product);
}
