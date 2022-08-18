package com.worktrial.task.repositories;

import com.worktrial.task.enums.CustomerCategory;
import com.worktrial.task.models.Customer;
import com.worktrial.task.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findOrderByCustomer(Customer customer);

    @Query("select so from Order so where so.customer.category = :category")
    List<Order> findOrderByCustomerCategory(CustomerCategory category);
}
