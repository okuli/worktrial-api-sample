package com.worktrial.task.repositories;

import com.worktrial.task.models.OrderProduct;
import com.worktrial.task.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, String> {
    List<OrderProduct> findOrderProductByProduct(Product product);
}
